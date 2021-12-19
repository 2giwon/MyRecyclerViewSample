package com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.databinding.ItemAutoScrollBinding
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class AutoHorizontalScrollViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
): BaseViewHolder<PhotoVO, ItemAutoScrollBinding>(layoutResId, parent) {

    override fun onBindData(item: PhotoVO) {

    }
}
