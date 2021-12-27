package com.egiwon.myrecyclerviewsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.databinding.ItemScrollImageBinding
import com.egiwon.myrecyclerviewsample.ext.loadImageFromUrl
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class ImageViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
) {

    private val binding: ItemScrollImageBinding =
        requireNotNull(DataBindingUtil.bind(itemView))

    fun onBindData(item: PhotoVO) {
        binding.ivImage.loadImageFromUrl(item.regularImageUrl)
    }
}
