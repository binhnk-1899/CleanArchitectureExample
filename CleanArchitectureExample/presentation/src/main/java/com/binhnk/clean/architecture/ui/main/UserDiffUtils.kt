package com.binhnk.clean.architecture.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.binhnk.clean.architecture.model.UserItem


class UserDiffUtils(
        private val oldLst: ArrayList<UserItem>,
        private val newLst: ArrayList<UserItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun getOldListSize(): Int {
        return oldLst.size
    }

    override fun getNewListSize(): Int {
        return newLst.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLst[oldItemPosition] == newLst[newItemPosition]
    }
}