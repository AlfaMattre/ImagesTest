package com.aliaklukin.imagestest.data.model

import com.google.gson.annotations.SerializedName

data class AuthInfoResponse(
    @SerializedName("token") val token: String,
    @SerializedName("message") val message: String
)
