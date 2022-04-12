package com.egiwon.myrecyclerviewsample.data.model

import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO
import com.google.gson.annotations.SerializedName

data class RandomImageResponse(
    @SerializedName("description")
    val description: String? = "",
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
    val height: Int = 0,
    @SerializedName("user")
    val userResponse: UserResponse = UserResponse()
) : DataToViewObject<PhotoVO> {
    override fun toViewObject(): PhotoVO {
        return PhotoVO(
            id = id,
            description = description ?: "",
            likes = likes,
            fullImageUrl = urls.full,
            regularImageUrl = urls.regular,
            smallImageUrl = urls.small,
            thumb = urls.thumb,
            views = views,
            width = width,
            height = height,
            userId = userResponse.id,
            userName = userResponse.name,
            userNameId = userResponse.userName,
            userProfileImage = userResponse.profileImageResponse.largeImage
        )
    }

    fun toViewObjectArg(
        id: String = this.id,
        description: String = this.description ?: "",
        likes: Int = this.likes,
        fullImageUrl: String = this.urls.full,
        regularImageUrl: String = this.urls.regular,
        smallImageUrl: String = this.urls.small,
        thumb: String = this.urls.thumb,
        views: Int = this.views,
        width: Int = this.width,
        height: Int = this.height,
        userId: String = this.userResponse.id,
        userName: String = this.userResponse.name,
        userNameId: String = this.userResponse.userName,
        userProfileImage: String = this.userResponse.profileImageResponse.largeImage,
        selected: Boolean = false
    ): PhotoVO {
        return PhotoVO(
            id = id,
            description = description,
            likes = likes,
            fullImageUrl = fullImageUrl,
            regularImageUrl = regularImageUrl,
            smallImageUrl = smallImageUrl,
            thumb = thumb,
            views = views,
            width = width,
            height = height,
            userId = userId,
            userName = userName,
            userNameId = userNameId,
            userProfileImage = userProfileImage,
            selected = selected
        )
    }

}
