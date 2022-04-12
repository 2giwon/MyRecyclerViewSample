package com.egiwon.myrecyclerviewsample.ui.user

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.databinding.ItemUserProfileImageBinding
import com.egiwon.myrecyclerviewsample.ext.loadImageFromUrl
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO

class UserProfileItemViewHolder(
    @LayoutRes layoutResId: Int, parent: ViewGroup
) : BaseViewHolder<PhotoVO>(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
) {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ItemUserProfileImageBinding.bind(itemView)
    }

    private val context: Context = binding.root.context

    override fun bindData(item: PhotoVO?) {
        item ?: return

        binding.ivProfile.loadImageFromUrl(item.userProfileImage)
        if (item.selected) {
            binding.tvName.setTextColor(Color.BLUE)
            binding.tvName.text = SpannableStringBuilder(item.userName).apply {
                setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    item.userName.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        } else {
            binding.tvName.text = item.userName
            binding.tvName.setTextColor(ContextCompat.getColor(context, R.color.black))
        }

        binding.executePendingBindings()
    }

}
