package com.egiwon.myrecyclerviewsample.ui.model

data class RecyclerItem<ITEM>(
    val itemViewType: Int = 0,
    val item: ITEM?
)
