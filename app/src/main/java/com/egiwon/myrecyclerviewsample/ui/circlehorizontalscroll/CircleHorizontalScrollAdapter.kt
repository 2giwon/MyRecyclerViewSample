package com.egiwon.myrecyclerviewsample.ui.circlehorizontalscroll

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll.AutoHorizontalScrollViewHolder
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class CircleHorizontalScrollAdapter: RecyclerView.Adapter<AutoHorizontalScrollViewHolder>() {

    private val list = mutableListOf<PhotoVO>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AutoHorizontalScrollViewHolder {
        return AutoHorizontalScrollViewHolder(R.layout.item_scroll_image, parent)
    }

    override fun onBindViewHolder(holder: AutoHorizontalScrollViewHolder, position: Int) {
        holder.onBindData(list[position % list.size])
    }

    override fun getItemCount(): Int = if (list.size > 0) Int.MAX_VALUE else 0

    fun replaceItems(items: List<PhotoVO>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}
