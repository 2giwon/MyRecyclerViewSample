package com.egiwon.myrecyclerviewsample.ui.customscrollbar

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.ui.ImageViewHolder
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class CustomScrollAdapter(
    @LayoutRes private val layoutResId: Int
): RecyclerView.Adapter<ImageViewHolder>() {

    private val list = mutableListOf<PhotoVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(layoutResId, parent)
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBindData(list[position])
    }


    override fun getItemCount(): Int = list.size

    fun replaceItems(items: List<PhotoVO>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}
