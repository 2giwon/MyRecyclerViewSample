package com.egiwon.myrecyclerviewsample.data

import com.egiwon.myrecyclerviewsample.data.model.RandomImageResponse
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO
import io.reactivex.Single
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDataSource: ImageDataSource
) : ImageRepository {
    override fun fetchRandomImage(count: Int): Single<List<PhotoVO>> {
        return imageDataSource.fetchRandomImages(count).map { list ->
            list.map(RandomImageResponse::toViewObject)
        }
    }
}
