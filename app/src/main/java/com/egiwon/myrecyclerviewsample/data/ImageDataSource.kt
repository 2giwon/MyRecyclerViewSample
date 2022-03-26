package com.egiwon.myrecyclerviewsample.data

import com.egiwon.myrecyclerviewsample.data.model.RandomImageResponse
import io.reactivex.Single

interface ImageDataSource {
    suspend fun fetchRandomImages(count: Int): List<RandomImageResponse>

    suspend fun fetchImageFromUser(userName: String): List<RandomImageResponse>
}
