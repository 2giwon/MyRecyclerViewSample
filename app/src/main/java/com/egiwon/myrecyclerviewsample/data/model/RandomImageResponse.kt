package com.egiwon.myrecyclerviewsample.data.model

import com.google.gson.annotations.SerializedName

data class RandomImageResponse(
    @SerializedName("description")
    val description: Any = Any(),
    @SerializedName("id")
    val id: String = "",
    @SerializedName("liked_by_user")
    val likedByUser: Boolean = false,
    @SerializedName("likes")
    val likes: Int = 0,
    @SerializedName("urls")
    val urls: Urls = Urls(),
    @SerializedName("views")
    val views: Int = 0,
    @SerializedName("width")
    val width: Int = 0,
    @SerializedName("height")
    val height: Int = 0
)
