package com.aliaklukin.imagestest.data.model

import com.google.gson.annotations.SerializedName

data class HitsResponse(
    @SerializedName("hits") val hits: List<HitResponse>
)
