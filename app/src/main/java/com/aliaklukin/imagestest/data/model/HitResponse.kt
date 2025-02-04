package com.aliaklukin.imagestest.data.model

import com.google.gson.annotations.SerializedName

data class HitResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("pageURL") val pageURL: String,
    @SerializedName("type") val type: String,
    @SerializedName("tags") val tags: String,
    @SerializedName("previewURL") val previewURL: String,
    @SerializedName("webFormatURL") val webFormatURL: String,
    @SerializedName("largeImageURL") val largeImageURL: String,
    @SerializedName("imageSize") val imageSize: Int,
    @SerializedName("views") val views: Int,
    @SerializedName("downloads") val downloads: Int,
    @SerializedName("likes") val likes: Int,
    @SerializedName("comments") val comments: Int,
    @SerializedName("user") val user: String,
    @SerializedName("userImageURL") val userImageURL: String
)
