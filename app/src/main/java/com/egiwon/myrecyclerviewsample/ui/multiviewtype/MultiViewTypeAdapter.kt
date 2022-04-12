package com.egiwon.myrecyclerviewsample.ui.multiviewtype

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.base.BaseViewHolder
import com.egiwon.myrecyclerviewsample.ui.ImageViewModel
import com.egiwon.myrecyclerviewsample.ui.UserProfileImagesViewHolder
import com.egiwon.myrecyclerviewsample.ui.ViewType
import com.egiwon.myrecyclerviewsample.ui.model.RecyclerItem
import com.egiwon.myrecyclerviewsample.ui.verticalperformance.ImageListViewHolder

class MultiViewTypeAdapter(
    private val viewModel: ImageViewModel
) : RecyclerView.Adapter<BaseViewHolder<Any>>() {

    private val list = mutableListOf<RecyclerItem<*>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        @Suppress("UNCHECKED_CAST")
        return when (viewType) {
            ViewType.IMAGE_LIST.ordinal -> ImageListViewHolder(parent)
            ViewType.USER_IMAGE_LIST.ordinal -> UserProfileImagesViewHolder(parent, viewModel)
            else -> object : BaseViewHolder<Any>(View(parent.context)) {
                override fun bindData(item: Any?) {}

            }
        } as BaseViewHolder<Any>
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.bindData(list[position].item)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return list[position].itemViewType
    }

    fun replaceItems(items: List<RecyclerItem<*>>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}
