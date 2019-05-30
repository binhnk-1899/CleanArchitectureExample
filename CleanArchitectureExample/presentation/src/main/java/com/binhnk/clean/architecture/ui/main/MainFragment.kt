package com.binhnk.clean.architecture.ui.main

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binhnk.clean.architecture.R
import com.binhnk.clean.architecture.base.BaseFragment
import com.binhnk.clean.architecture.model.UserItem
import com.binhnk.clean.architecture.util.Utils
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.sharedViewModel

class MainFragment :
    BaseFragment<com.binhnk.clean.architecture.databinding.FragmentMainBinding, MainViewModel>() {
    private val mContext: Context by lazy { activity!!.applicationContext }

    override val viewModel: MainViewModel by sharedViewModel()
    override val layoutId: Int
        get() = R.layout.fragment_main

    companion object {
        fun getInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe_refresh_layout.isEnabled = false
        rv_user.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        val mAdapter = UserAdapter(mContext, true, object : UserAdapter.Callback {
            override fun onItemClicked(mUserClicked: UserItem) {
                viewModel.insertUserToDB(mUserClicked)
            }

            override fun onItemLongClicked(mUserClicked: UserItem) {
                Utils.shortToast(mContext, "${mUserClicked.page}")
            }

        })
        rv_user.adapter = mAdapter

        viewModel.run {
            clientData.observe(this@MainFragment, Observer {
                mAdapter.updateAdapter(ArrayList(it))
                rv_user.smoothScrollToPosition(0)
                if (it.isNullOrEmpty()) {
                    tv_error.visibility = View.VISIBLE
                } else {
                    tv_error.visibility = View.GONE
                }
            })

            page.observe(this@MainFragment, Observer {
                if (it in 1..4) {
                    getUser()
                } else {
                    getAllUser()
                }
                updatePageState(it)
            })

            loadingClient.observe(this@MainFragment, Observer {
                swipe_refresh_layout.isRefreshing = it
            })

            insertUserSuccess.observe(this@MainFragment, Observer {
                if (it != null) {
                    mAdapter.updateStatePosition(it.id)
                    Utils.shortToast(mContext, "${it.firstName} was inserted into database")
                    insertUserSuccess.value = null
                }
            })

            insertUserFailure.observe(this@MainFragment, Observer {
                if (it != null) {
                    Utils.shortToast(mContext, "${it.firstName} insert failed")
                    insertUserFailure.value = null
                }
            })

            connectivityChanged.observe(this@MainFragment, Observer {
                if (page.value in 1..4) {
                    getUser()
                } else {
                    getAllUser()
                }
            })
        }
    }

    private fun updatePageState(mPage: Int) {
        when (mPage) {
            1 -> {
                tv_page_1.setTextColor(Color.WHITE)
                tv_page_2.setTextColor(Color.BLACK)
                tv_page_3.setTextColor(Color.BLACK)
                tv_page_4.setTextColor(Color.BLACK)
                tv_all.setTextColor(Color.BLACK)

                tv_page_1.setBackgroundResource(R.drawable.bg_button_selected)
                tv_page_2.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_3.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_4.setBackgroundResource(R.drawable.bg_button_normal)
                tv_all.setBackgroundResource(R.drawable.bg_button_normal)
            }
            2 -> {
                tv_page_1.setTextColor(Color.BLACK)
                tv_page_2.setTextColor(Color.WHITE)
                tv_page_3.setTextColor(Color.BLACK)
                tv_page_4.setTextColor(Color.BLACK)
                tv_all.setTextColor(Color.BLACK)

                tv_page_1.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_2.setBackgroundResource(R.drawable.bg_button_selected)
                tv_page_3.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_4.setBackgroundResource(R.drawable.bg_button_normal)
                tv_all.setBackgroundResource(R.drawable.bg_button_normal)
            }
            3 -> {
                tv_page_1.setTextColor(Color.BLACK)
                tv_page_2.setTextColor(Color.BLACK)
                tv_page_3.setTextColor(Color.WHITE)
                tv_page_4.setTextColor(Color.BLACK)
                tv_all.setTextColor(Color.BLACK)

                tv_page_1.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_2.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_3.setBackgroundResource(R.drawable.bg_button_selected)
                tv_page_4.setBackgroundResource(R.drawable.bg_button_normal)
                tv_all.setBackgroundResource(R.drawable.bg_button_normal)
            }
            4 -> {
                tv_page_1.setTextColor(Color.BLACK)
                tv_page_2.setTextColor(Color.BLACK)
                tv_page_3.setTextColor(Color.BLACK)
                tv_page_4.setTextColor(Color.WHITE)
                tv_all.setTextColor(Color.BLACK)

                tv_page_1.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_2.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_3.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_4.setBackgroundResource(R.drawable.bg_button_selected)
                tv_all.setBackgroundResource(R.drawable.bg_button_normal)
            }
            else -> {
                tv_page_1.setTextColor(Color.BLACK)
                tv_page_2.setTextColor(Color.BLACK)
                tv_page_3.setTextColor(Color.BLACK)
                tv_page_4.setTextColor(Color.BLACK)
                tv_all.setTextColor(Color.WHITE)

                tv_page_1.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_2.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_3.setBackgroundResource(R.drawable.bg_button_normal)
                tv_page_4.setBackgroundResource(R.drawable.bg_button_normal)
                tv_all.setBackgroundResource(R.drawable.bg_button_selected)
            }
        }
    }
}