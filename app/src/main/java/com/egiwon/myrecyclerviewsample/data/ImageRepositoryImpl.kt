package com.egiwon.myrecyclerviewsample.data

import com.egiwon.myrecyclerviewsample.data.model.RandomImageResponse
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDataSource: ImageDataSource
) : ImageRepository {

    override suspend fun fetchRandomImage(count: Int): Result<List<PhotoVO>> =
        withContext(Dispatchers.IO) {
            runCatching {
                imageDataSource.fetchRandomImages(count).mapIndexed { index, randomImageResponse ->
                    randomImageResponse.toViewObjectArg(selected = index == 0)
                }
            }
        }

    override suspend fun fetchImageFromUser(userName: String): Result<List<PhotoVO>> =
        withContext(Dispatchers.IO) {
            runCatching {
                imageDataSource.fetchImageFromUser(userName)
                    .map(RandomImageResponse::toViewObject)
            }
        }
}
