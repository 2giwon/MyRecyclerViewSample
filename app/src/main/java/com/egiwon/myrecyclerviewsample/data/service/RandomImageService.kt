package com.egiwon.myrecyclerviewsample.data.service

import com.egiwon.myrecyclerviewsample.data.model.RandomImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomImageService {
    @GET("/photos/random")
    fun fetchRandomImage(
        @Query("count") count: Int
    ): Single<List<RandomImageResponse>>

    @GET("/users/{username}/photos")
    fun fetchUserPhotos(
        @Path("username")
        userName: String
    ): Single<List<RandomImageResponse>>
}
