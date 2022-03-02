package com.egiwon.myrecyclerviewsample.data.model

import com.google.gson.annotations.SerializedName

data class ProfileImageResponse(
    @SerializedName("large")
    val largeImage: String = "",
    @SerializedName("medium")
    val mediumImage: String = ""
)
