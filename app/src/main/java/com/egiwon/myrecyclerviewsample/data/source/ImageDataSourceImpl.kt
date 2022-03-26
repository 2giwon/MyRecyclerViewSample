package com.egiwon.myrecyclerviewsample.data.source

import com.egiwon.myrecyclerviewsample.data.ImageDataSource
import com.egiwon.myrecyclerviewsample.data.service.RandomImageService
import com.egiwon.myrecyclerviewsample.data.model.RandomImageResponse
import io.reactivex.Single
import javax.inject.Inject

class ImageDataSourceImpl @Inject constructor(
    private val randomImageService: RandomImageService
) : ImageDataSource {
    override suspend fun fetchRandomImages(count: Int): List<RandomImageResponse> {
        return randomImageService.fetchRandomImage(count)
    }

    override suspend fun fetchImageFromUser(userName: String): List<RandomImageResponse> {
        return randomImageService.fetchUserPhotos(userName)
    }
}
