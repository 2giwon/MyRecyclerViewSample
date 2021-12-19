package com.egiwon.myrecyclerviewsample.data.model

interface DataToViewObject<T> {
    fun toViewObject(): T
}
