package com.egiwon.myrecyclerviewsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.databinding.ItemTitleImageBinding
import com.egiwon.myrecyclerviewsample.ext.loadImageFromUrl
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class ImageTitleViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
) {
    val binding: ItemTitleImageBinding by lazy(LazyThreadSafetyMode.NONE) {
        ItemTitleImageBinding.bind(itemView)
    }

    fun onBind(item: PhotoVO) {
        binding.tvTitle.text = item.likes.toString()
        binding.ivImage.loadImageFromUrl(item.regularImageUrl)
    }
}
