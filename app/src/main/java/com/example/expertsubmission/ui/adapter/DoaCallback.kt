package com.example.expertsubmission.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.model.Doa

class DoaCallback(private val oldList: List<Doa>, private val newList: List<Doa>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].doa != newList[newItemPosition].doa -> {
                false
            }
            else -> true
        }
    }
}