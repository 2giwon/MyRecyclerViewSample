package com.egiwon.myrecyclerviewsample.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ITEM: Any, VDB: ViewDataBinding>(
    @LayoutRes layoutResId: Int
): RecyclerView.Adapter<BaseViewHolder<ITEM>>() {

    private val list = mutableListOf<ITEM>()

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM>, position: Int) {
        holder.bindData(list[position])
    }

    fun replaceItems(items: List<ITEM>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}
