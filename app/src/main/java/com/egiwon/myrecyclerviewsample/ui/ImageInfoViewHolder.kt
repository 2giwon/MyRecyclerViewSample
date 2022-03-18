package com.egiwon.myrecyclerviewsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.databinding.ItemVerticalImageBinding
import com.egiwon.myrecyclerviewsample.ext.loadImageFromUrl
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class ImageInfoViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
): BaseViewHolder<PhotoVO>(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
) {
    val binding: ItemVerticalImageBinding by lazy(LazyThreadSafetyMode.NONE) {
        ItemVerticalImageBinding.bind(itemView)
    }

    override fun bindData(item: PhotoVO?) {
        item?.run {
            binding.ivImage.loadImageFromUrl(regularImageUrl)
            binding.tvDescription.text = description
            binding.tvLikes.text = likes.toString()
            binding.executePendingBindings()
        }
    }
}
