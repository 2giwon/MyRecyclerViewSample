package com.egiwon.myrecyclerviewsample.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ITEM : Any, VDB : ViewDataBinding> : RecyclerView.Adapter<BaseViewHolder<ITEM, VDB>>() {

    private val list = mutableListOf<ITEM>()

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM, VDB>, position: Int) {
        holder.onBindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    @Suppress("NotifyDataSetChanged")
    fun replaceItems(items: List<ITEM>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}
