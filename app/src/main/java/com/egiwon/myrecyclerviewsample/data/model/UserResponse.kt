package com.egiwon.myrecyclerviewsample.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("username")
    val userName: String = "",
    @SerializedName("profile_image")
    val profileImageResponse: ProfileImageResponse = ProfileImageResponse()
)
