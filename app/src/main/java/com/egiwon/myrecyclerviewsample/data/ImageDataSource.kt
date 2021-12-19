package com.egiwon.myrecyclerviewsample.data

import com.egiwon.myrecyclerviewsample.data.model.RandomImageResponse
import io.reactivex.Single

interface ImageDataSource {
    fun fetchRandomImages(count: Int): Single<List<RandomImageResponse>>
}
