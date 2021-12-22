package com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class AutoHorizontalScrollAdapter(
    @LayoutRes private val layoutResId: Int
) : RecyclerView.Adapter<AutoHorizontalScrollViewHolder>() {

    private val list = mutableListOf<PhotoVO>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AutoHorizontalScrollViewHolder {
        return AutoHorizontalScrollViewHolder(layoutResId, parent)
    }

    override fun onBindViewHolder(holder: AutoHorizontalScrollViewHolder, position: Int) {
        holder.onBindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    @Suppress("NotifyDataSetChanged")
    fun replaceItems(items: List<PhotoVO>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}
