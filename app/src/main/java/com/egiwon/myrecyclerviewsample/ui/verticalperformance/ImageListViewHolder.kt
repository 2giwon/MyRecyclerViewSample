package com.egiwon.myrecyclerviewsample.ui.verticalperformance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class ImageListViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
): BaseViewHolder<PhotoVO>(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
) {
    override fun bindData(item: PhotoVO?) {
    }
}
