package com.egiwon.myrecyclerviewsample.data

import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO
import io.reactivex.Single

interface ImageRepository {
    suspend fun fetchRandomImage(count: Int): Result<List<PhotoVO>>

    suspend fun fetchImageFromUser(userName: String): Result<List<PhotoVO>>
}
