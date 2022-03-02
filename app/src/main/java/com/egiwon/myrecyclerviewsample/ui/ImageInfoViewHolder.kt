package com.egiwon.myrecyclerviewsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.databinding.ItemVerticalImagesBinding
import com.egiwon.myrecyclerviewsample.ext.loadImageFromUrl
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class ImageInfoViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
) {
    val binding: ItemVerticalImagesBinding by lazy(LazyThreadSafetyMode.NONE) {
        ItemVerticalImagesBinding.bind(itemView)
    }

    fun onBind(item: PhotoVO) {
        binding.ivImage.loadImageFromUrl(item.regularImageUrl)
        binding.tvDescription.text = item.description
        binding.tvLikes.text = item.likes.toString()
        binding.executePendingBindings()
    }
}
