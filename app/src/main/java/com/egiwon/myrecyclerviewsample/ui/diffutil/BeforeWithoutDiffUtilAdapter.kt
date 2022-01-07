package com.egiwon.myrecyclerviewsample.ui.diffutil

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.ui.ImageTitleViewHolder
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class BeforeWithoutDiffUtilAdapter(
    @LayoutRes private val layoutResId: Int
): RecyclerView.Adapter<ImageTitleViewHolder>() {

    private val list = mutableListOf<PhotoVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageTitleViewHolder {
        return ImageTitleViewHolder(layoutResId, parent)
    }

    override fun onBindViewHolder(holder: ImageTitleViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun replaceItems(items: List<PhotoVO>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}
