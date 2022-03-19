package com.egiwon.myrecyclerviewsample.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.databinding.ItemUserProfileImageBinding
import com.egiwon.myrecyclerviewsample.ext.loadImageFromUrl
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class UserProfileItemViewHolder(
    @LayoutRes layoutResId: Int, parent: ViewGroup
): BaseViewHolder<PhotoVO>(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
) {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ItemUserProfileImageBinding.bind(itemView)
    }

    override fun bindData(item: PhotoVO?) {
        item ?: return

        binding.ivProfile.loadImageFromUrl(item.userProfileImage)
        binding.tvName.text = item.userName
        binding.executePendingBindings()
    }

}
