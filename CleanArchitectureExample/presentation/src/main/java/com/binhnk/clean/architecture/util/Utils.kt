package com.binhnk.clean.architecture.util

import android.content.Context
import android.widget.Toast

class Utils {

    companion object {
        fun shortToast(mContext: Context, message:String) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}