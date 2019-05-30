package com.binhnk.clean.architecture.ui.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.binhnk.clean.architecture.base.BaseViewModel
import com.binhnk.clean.architecture.domain.usecase.user.GetUserUseCase
import com.binhnk.clean.architecture.domain.usecase.user.InsertToDBUseCase
import com.binhnk.clean.architecture.domain.usecase.user.QueryAllUserUseCase
import com.binhnk.clean.architecture.domain.usecase.user.QueryUserByIDUseCase
import com.binhnk.clean.architecture.model.UserItem
import com.binhnk.clean.architecture.model.UserItemMapper
import com.binhnk.clean.architecture.rx.SchedulerProvider
import com.binhnk.clean.architecture.util.SingleLiveEvent
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function4

class MainViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val insertUserUseCase: InsertToDBUseCase,
    private val queryUserByIDUseCase: QueryUserByIDUseCase,
    private val queryAllUserUseCase: QueryAllUserUseCase,
    private val schedulerProvider: SchedulerProvider,
    private val userItemMapper: UserItemMapper
) : BaseViewModel(getUserUseCase, insertUserUseCase, queryUserByIDUseCase) {

    val clientData = MutableLiveData<List<UserItem>>()
    val localData = MutableLiveData<List<UserItem>>()
    val page = MutableLiveData<Int>().apply {
        postValue(1)
    }
    val loadingClient = MutableLiveData<Boolean>().apply {
        postValue(false)
    }
    val loadingLocal = MutableLiveData<Boolean>().apply {
        postValue(false)
    }
    val insertUserSuccess = MutableLiveData<UserItem>()
    val insertUserFailure = MutableLiveData<UserItem>()
    val connectivityChanged = SingleLiveEvent<Unit>()

    /**
     * get user per page
     */
    fun getUser() {
        page.value?.let { input ->
            loadingClient.postValue(true)

            compositeDisposable.add(
                getUserSingle(input)
                    .doFinally { loadingClient.postValue(false) }
                    .subscribe(
                        { users ->
                            clientData.postValue(users)
                            Log.e("Ahihi", "${users.size}")
                        }, {
                            clientData.postValue(ArrayList())
                            it.printStackTrace()
                            Log.e("Ahihi", it.localizedMessage!!)
                        }
                    )
            )
        }
    }

    /**
     * get userItem single of page
     */
    private fun getUserSingle(page: Int): Single<List<UserItem>> {
        return getUserUseCase.createObservable(GetUserUseCase.Params(page = page))
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .map { users ->
                users.map { user ->
                    user.page = page
                    checkUserState(userItemMapper.mapToPresentation(user))
                }
            }
    }

    /**
     * get users from all pages
     */
    @SuppressLint("CheckResult")
    fun getAllUser() {
        loadingClient.postValue(true)

        val userObservable1 = getUserSingle(1)
        val userObservable2 = getUserSingle(2)
        val userObservable3 = getUserSingle(3)
        val userObservable4 = getUserSingle(4)

        // zip to collect observables
        Single.zip(userObservable1, userObservable2, userObservable3, userObservable4,
            Function4<List<UserItem>, List<UserItem>, List<UserItem>, List<UserItem>, List<UserItem>> { t1, t2, t3, t4 ->
                collectAllList(t1, t2, t3, t4)
            })
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doFinally { loadingClient.value = false }
            .subscribe(
                { result ->
                    clientData.postValue(result)
                },
                {
                    clientData.postValue(ArrayList())
                    Throwable("Data not found")
                }
            )
    }

    /**
     * check user exist in database
     */
    private fun checkUserState(mUser: UserItem): UserItem {
        compositeDisposable.add(
            queryUserByIDUseCase.createObservable(QueryUserByIDUseCase.Params(mUser.id))
                .map { result ->
                    userItemMapper.mapToPresentation(result)
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.io())
                .subscribe({
                    mUser.addedInDB = true
                    Log.e("Ahihi", "Not null")
                }, {
                    mUser.addedInDB = false
                    Log.e("Ahihi", "Null")
                })
        )
        return mUser
    }

    /**
     * collect all list
     */
    private fun collectAllList(
        l1: List<UserItem>,
        l2: List<UserItem>,
        l3: List<UserItem>,
        l4: List<UserItem>
    ): ArrayList<UserItem> {
        val tmp = ArrayList<UserItem>()
        tmp.addAll(l1)
        tmp.addAll(l2)
        tmp.addAll(l3)
        tmp.addAll(l4)
        return tmp
    }

    /**
     * change page
     */
    fun changePage(mPage: Int) {
        if (mPage != page.value) {
            page.postValue(mPage)
        }
    }

    /**
     * insert user to database
     */
    fun insertUserToDB(mUser: UserItem) {
        Completable.fromAction {
            insertUserUseCase.createObservable(
                InsertToDBUseCase.Params(
                    userItemMapper.mapToDomain(
                        mUser
                    )
                )
            )
        }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    insertUserSuccess.value = mUser
                    Log.e("Ahihi", "Insert Complete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    insertUserFailure.value = mUser
                    Log.e("Ahihi", "Insert Error")
                }
            })
    }

    /**
     * query all user in Room database
     */
    fun queryAllUser() {
        loadingLocal.postValue(true)

        compositeDisposable.add(
            queryAllUserUseCase.createObservable(null)
                .map { users ->
                    users.map {
                        userItemMapper.mapToPresentation(it)
                    }
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doFinally { loadingLocal.postValue(false) }
                .subscribe(
                    { result ->
                        localData.postValue(result)
                    },
                    {
                        Throwable("Data not found")
                    }
                )
        )
    }
}