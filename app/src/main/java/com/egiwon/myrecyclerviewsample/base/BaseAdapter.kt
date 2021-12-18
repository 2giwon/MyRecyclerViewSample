package com.egiwon.myrecyclerviewsample.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ITEM : Any, VDB : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : RecyclerView.Adapter<BaseViewHolder<ITEM, VDB>>() {

    private val list = mutableListOf<ITEM>()

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM, VDB>, position: Int) {
        holder.onBindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun replaceItems(items: List<ITEM>) {
        list.clear()
        list.addAll(items)
        notifyItemRangeChanged(0, items.size)
    }
}
