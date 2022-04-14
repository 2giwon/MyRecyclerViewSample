package com.egiwon.myrecyclerviewsample.ui.verticalperformance

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.R

class ImageItemDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val lp: GridLayoutManager.LayoutParams = view.layoutParams as GridLayoutManager.LayoutParams

        outRect.top = view.context.resources.getDimensionPixelOffset(R.dimen.offset8)

        when (lp.spanIndex) {
            0 -> {
                outRect.left = view.context.resources.getDimensionPixelOffset(R.dimen.offset16)
            }
            2 -> {
                outRect.left = view.context.resources.getDimensionPixelOffset(R.dimen.offset8)
                outRect.right = view.context.resources.getDimensionPixelOffset(R.dimen.offset8)
            }
            4 -> {
                outRect.right = view.context.resources.getDimensionPixelOffset(R.dimen.offset16)
            }
        }
    }
}
