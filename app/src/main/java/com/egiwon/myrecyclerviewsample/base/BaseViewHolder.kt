package com.egiwon.myrecyclerviewsample.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<ITEM>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindData(item: ITEM?)
}
