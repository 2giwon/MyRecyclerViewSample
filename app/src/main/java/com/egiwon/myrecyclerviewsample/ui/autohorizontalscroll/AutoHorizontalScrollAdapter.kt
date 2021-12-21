package com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.myrecyclerviewsample.base.BaseAdapter
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.databinding.ItemScrollImageBinding
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class AutoHorizontalScrollAdapter(
    @LayoutRes private val layoutResId: Int
) : BaseAdapter<PhotoVO, ItemScrollImageBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<PhotoVO, ItemScrollImageBinding> {
        return AutoHorizontalScrollViewHolder(layoutResId, parent)
    }
}
