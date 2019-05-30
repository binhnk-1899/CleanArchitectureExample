package com.binhnk.clean.architecture.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import com.binhnk.clean.architecture.R
import com.binhnk.clean.architecture.base.BaseFragment
import org.koin.androidx.viewmodel.ext.sharedViewModel

class StorageFragment :
    BaseFragment<com.binhnk.clean.architecture.databinding.FragmentStorageBinding, MainViewModel>() {
    override val viewModel: MainViewModel by sharedViewModel()
    override val layoutId: Int
        get() = R.layout.fragment_storage

    private val mContext: Context by lazy { activity!!.applicationContext }

    companion object {
        fun getInstance() = StorageFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}