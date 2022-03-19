package com.egiwon.myrecyclerviewsample.ui.model

data class PhotoVO(
    val id: String = "",
    val description: String = "",
    val likes: Int = 0,
    val fullImageUrl: String = "",
    val regularImageUrl: String = "",
    val smallImageUrl: String = "",
    val thumb: String = "",
    val views: Int = 0,
    val width: Int = 0,
    val height: Int = 0,
    val userId: String = "",
    val userName: String = "",
    val userProfileImage: String = "",
    val selected: Boolean = false
)
