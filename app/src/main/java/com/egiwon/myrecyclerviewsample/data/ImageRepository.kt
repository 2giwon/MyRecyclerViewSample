package com.egiwon.myrecyclerviewsample.data

import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO
import io.reactivex.Single

interface ImageRepository {
    fun fetchRandomImage(count: Int): Single<List<PhotoVO>>
}
