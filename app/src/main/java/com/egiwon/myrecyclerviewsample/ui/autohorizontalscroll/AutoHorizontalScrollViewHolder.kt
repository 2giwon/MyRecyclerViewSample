package com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.databinding.ItemScrollImageBinding
import com.egiwon.myrecyclerviewsample.ext.loadImageFromUrl
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class AutoHorizontalScrollViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
): BaseViewHolder<PhotoVO, ItemScrollImageBinding>(layoutResId, parent) {

    override fun onBindData(item: PhotoVO) {
        binding.ivImage.loadImageFromUrl(item.regularImageUrl)
    }
}
