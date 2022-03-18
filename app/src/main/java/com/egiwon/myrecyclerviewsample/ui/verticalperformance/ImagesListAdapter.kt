package com.egiwon.myrecyclerviewsample.ui.verticalperformance

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.myrecyclerviewsample.base.BaseAdapter
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.databinding.ItemVerticalImageBinding
import com.egiwon.myrecyclerviewsample.ui.ImageInfoViewHolder
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class ImagesListAdapter(
    @LayoutRes private val layoutResId: Int
): BaseAdapter<PhotoVO, ItemVerticalImageBinding>(layoutResId) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PhotoVO> {
        return ImageInfoViewHolder(layoutResId, parent)
    }
}
