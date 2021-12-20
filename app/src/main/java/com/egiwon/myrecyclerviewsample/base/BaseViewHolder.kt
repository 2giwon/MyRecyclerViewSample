package com.egiwon.myrecyclerviewsample.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<ITEM: Any, VDB: ViewDataBinding>(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
) {
    val binding: VDB = requireNotNull(DataBindingUtil.bind(itemView))

    abstract fun onBindData(item: ITEM)
}
