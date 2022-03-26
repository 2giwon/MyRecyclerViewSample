package com.egiwon.myrecyclerviewsample.data.service

import com.egiwon.myrecyclerviewsample.data.model.RandomImageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomImageService {
    @GET("/photos/random")
    suspend fun fetchRandomImage(
        @Query("count") count: Int
    ): List<RandomImageResponse>

    @GET("/users/{username}/photos")
    suspend fun fetchUserPhotos(
        @Path("username")
        userName: String
    ): List<RandomImageResponse>
}
