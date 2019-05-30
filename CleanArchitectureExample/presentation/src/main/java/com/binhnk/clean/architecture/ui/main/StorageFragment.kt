package com.binhnk.clean.architecture.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binhnk.clean.architecture.R
import com.binhnk.clean.architecture.base.BaseFragment
import com.binhnk.clean.architecture.model.UserItem
import kotlinx.android.synthetic.main.fragment_storage.*
import org.koin.androidx.viewmodel.ext.sharedViewModel

class StorageFragment :
    BaseFragment<com.binhnk.clean.architecture.databinding.FragmentStorageBinding, MainViewModel>(),
    UserAdapter.Callback {

    override val viewModel: MainViewModel by sharedViewModel()
    override val layoutId: Int
        get() = R.layout.fragment_storage

    private val mContext: Context by lazy { activity!!.applicationContext }

    private var mUserAdapter: UserAdapter? = null

    companion object {
        fun getInstance() = StorageFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            localData.observe(this@StorageFragment, Observer { data ->
                mUserAdapter?.let {
                    it.updateAdapter(ArrayList(data))
                }
            })

            queryAllUser()
        }

        mUserAdapter = UserAdapter(mContext, false, this@StorageFragment)
        rv_user_storage.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        mUserAdapter?.let {
            rv_user_storage.adapter = it
        }
    }

    override fun onItemClicked(mUserClicked: UserItem) {

    }

    override fun onItemLongClicked(mUserClicked: UserItem) {

    }
}