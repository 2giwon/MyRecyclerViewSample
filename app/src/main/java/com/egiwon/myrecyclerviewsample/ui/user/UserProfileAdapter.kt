package com.egiwon.myrecyclerviewsample.ui.user

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.myrecyclerviewsample.base.BaseAdapter
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.databinding.ItemUserProfileImageBinding
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class UserProfileAdapter(
    @LayoutRes private val layoutResId: Int,
    private val onClick: (Int) -> Unit
) : BaseAdapter<PhotoVO, ItemUserProfileImageBinding>(layoutResId) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PhotoVO> {
        return UserProfileItemViewHolder(layoutResId, parent).apply {
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }
    }
}
