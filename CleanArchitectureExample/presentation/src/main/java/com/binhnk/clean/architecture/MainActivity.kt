package com.binhnk.clean.architecture

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.constraintlayout.widget.ConstraintSet
import com.binhnk.clean.architecture.base.BaseActivity
import com.binhnk.clean.architecture.databinding.ActivityMainBinding
import com.binhnk.clean.architecture.ui.main.MainFragment
import com.binhnk.clean.architecture.ui.main.MainViewModel
import com.binhnk.clean.architecture.ui.main.StorageFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mContext: Context by lazy { this@MainActivity }

    override val viewModel: MainViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.activity_main

    /**
     * transition for toolbar
     */
    private val toolbarConstraint1 = ConstraintSet()
    private val toolbarConstraint2 = ConstraintSet()
    private val autoTransition: AutoTransition by lazy {
        AutoTransition()
    }.apply {
        this.value.duration = 350
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainFragment.getInstance().let {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, it)
                .commitAllowingStateLoss()
        }

        initAction()

        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when(intent!!.action) {
                    "android.net.conn.CONNECTIVITY_CHANGE" -> {
                        viewModel.connectivityChanged.call()
                    }
                }
            }

        }, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    /**
     * init action for view
     */
    private fun initAction() {
        im_storage.setOnClickListener {
            toolbarConstraint2.clone(mContext, R.layout.storage_toolbar)
            TransitionManager.beginDelayedTransition(toolbar, autoTransition)
            toolbarConstraint2.applyTo(toolbar)
            tv_title.text = getString(R.string.user_storage)

            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_rtl,
                    R.anim.exit_rtl,
                    R.anim.enter_ltr,
                    R.anim.exit_ltr
                )
                .add(R.id.container, StorageFragment.getInstance())
                .addToBackStack("storage")
                .commitAllowingStateLoss()
        }

        im_back.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            toolbarConstraint1.clone(mContext, R.layout.main_toolbar)
            TransitionManager.beginDelayedTransition(toolbar, autoTransition)
            toolbarConstraint1.applyTo(toolbar)
            tv_title.text = getString(R.string.app_name)
        }
        super.onBackPressed()
    }
}
