package com.egiwon.myrecyclerviewsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseAdapter
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.databinding.ItemUserImagesBinding
import com.egiwon.myrecyclerviewsample.databinding.ItemUserProfileImageBinding
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO
import com.egiwon.myrecyclerviewsample.ui.model.Photos
import com.egiwon.myrecyclerviewsample.ui.user.UserProfileAdapter
import com.egiwon.myrecyclerviewsample.ui.verticalperformance.UserProfileImagesAdapter

class UserProfileImagesViewHolder(
    parent: ViewGroup,
    private val viewModel: ImageViewModel
): BaseViewHolder<Photos>(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user_images, parent, false)
) {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ItemUserImagesBinding.bind(itemView)
    }

    private var adapter: UserProfileAdapter? = null

    override fun bindData(item: Photos?) {
        item ?: return
        if (binding.rvUsers.adapter == null) {
            adapter = UserProfileAdapter(R.layout.item_user_profile_image) { selectedPosition ->
                viewModel.selectProfile(selectedPosition)
            }

            binding.rvUsers.adapter = adapter
            binding.rvUsers.setHasFixedSize(true)
        }

        adapter?.replaceItems(item.photoList)

        if (binding.rvImages.adapter == null) {
            binding.rvImages.adapter = UserProfileImagesAdapter(
                R.layout.item_scroll_image
            )

            binding.rvImages.setHasFixedSize(true)
        }

        val selectedPhoto = item.photoList.find { it.selected } ?: item.photoList.first()

        (binding.rvImages.adapter as? UserProfileImagesAdapter)?.replaceItems(selectedPhoto.userImages)
    }
}
