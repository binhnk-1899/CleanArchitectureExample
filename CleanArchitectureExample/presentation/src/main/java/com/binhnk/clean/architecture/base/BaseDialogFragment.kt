package com.binhnk.clean.architecture.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.binhnk.clean.architecture.R

abstract class BaseDialogFragment<ViewBinding : ViewDataBinding> : DialogFragment() {

    lateinit var viewBinding: ViewBinding

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.apply {
            isCancelable = false
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window!!.setLayout(
                resources.getDimensionPixelSize(R.dimen._250sdp),
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
    }
}