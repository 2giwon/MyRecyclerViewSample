package com.egiwon.myrecyclerviewsample.ui.diffutil

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.egiwon.myrecyclerviewsample.ui.ImageTitleViewHolder
import com.egiwon.myrecyclerviewsample.ui.ImageViewModel
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class AfterWithDiffUtilAdapter(
    @LayoutRes private val layoutResId: Int
): ListAdapter<PhotoVO, ImageTitleViewHolder>(
    object: DiffUtil.ItemCallback<PhotoVO>() {
        override fun areItemsTheSame(oldItem: PhotoVO, newItem: PhotoVO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoVO, newItem: PhotoVO): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageTitleViewHolder {
        return ImageTitleViewHolder(layoutResId, parent)
    }

    override fun onBindViewHolder(holder: ImageTitleViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun replaceItems(items: List<PhotoVO>) {
        submitList(items)
    }

}
