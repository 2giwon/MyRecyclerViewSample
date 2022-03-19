package com.egiwon.myrecyclerviewsample.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ITEM: Any, VDB: ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
): RecyclerView.Adapter<BaseViewHolder<ITEM>>() {

    private val list = mutableListOf<ITEM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ITEM> {
        return object : BaseViewHolder<ITEM>(
            LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        ) {
            override fun bindData(item: ITEM?) {}
        }
    }

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
