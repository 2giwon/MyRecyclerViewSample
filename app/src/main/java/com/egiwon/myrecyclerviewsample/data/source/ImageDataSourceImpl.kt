package com.egiwon.myrecyclerviewsample.data.source

import com.egiwon.myrecyclerviewsample.data.ImageDataSource
import com.egiwon.myrecyclerviewsample.data.service.RandomImageService
import com.egiwon.myrecyclerviewsample.data.model.RandomImageResponse
import io.reactivex.Single
import javax.inject.Inject

class ImageDataSourceImpl @Inject constructor(
    private val randomImageService: RandomImageService
) : ImageDataSource {
    override fun fetchRandomImages(count: Int): Single<List<RandomImageResponse>> {
        return randomImageService.fetchRandomImage(count)
    }
}
