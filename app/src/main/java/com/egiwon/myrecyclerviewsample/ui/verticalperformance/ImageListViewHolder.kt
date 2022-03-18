package com.egiwon.myrecyclerviewsample.ui.verticalperformance

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.databinding.ItemLayoutVerticalImagesBinding
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO
import com.egiwon.myrecyclerviewsample.ui.model.Photos

class ImageListViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
): BaseViewHolder<Photos>(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ItemLayoutVerticalImagesBinding.bind(itemView)
    }

    override fun bindData(item: Photos?) {
        item ?: return
        if (binding.rvImages.adapter == null) {
            binding.rvImages.adapter = ImagesListAdapter(
                R.layout.item_vertical_image
            )
            binding.rvImages.setHasFixedSize(true)
            binding.rvImages.addItemDecoration(ImageItemDecoration())
            binding.rvImages.isNestedScrollingEnabled = false
        }

        (binding.rvImages.adapter as ImagesListAdapter).replaceItems(item.photoList)
    }

    inner class ImageItemDecoration: RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = view.context.resources.getDimensionPixelOffset(R.dimen.offset8)
            outRect.left = view.context.resources.getDimensionPixelOffset(R.dimen.offset8)
        }
    }

}
