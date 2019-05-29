package com.binhnk.clean.architecture

import android.content.Context
import android.os.Bundle
import com.binhnk.clean.architecture.base.BaseActivity
import com.binhnk.clean.architecture.databinding.ActivityMainBinding
import com.binhnk.clean.architecture.ui.main.MainFragment
import com.binhnk.clean.architecture.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mContext: Context by lazy { this@MainActivity }

    override val viewModel: MainViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainFragment.getInstance().let {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, it)
                    .commitAllowingStateLoss()
        }
    }
}
