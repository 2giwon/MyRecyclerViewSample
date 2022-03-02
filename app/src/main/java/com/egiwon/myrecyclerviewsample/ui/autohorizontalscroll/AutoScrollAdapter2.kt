package com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.ui.ImageViewHolder
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class AutoScrollAdapter2: ListAdapter<PhotoVO, ImageViewHolder>(diffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(R.layout.item_scroll_image, parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBindData(getItem(position))
    }

    companion object {
        val diffCallBack = object: DiffUtil.ItemCallback<PhotoVO>() {
            override fun areItemsTheSame(oldItem: PhotoVO, newItem: PhotoVO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotoVO, newItem: PhotoVO): Boolean {
                return oldItem == newItem
            }

        }
    }
}
