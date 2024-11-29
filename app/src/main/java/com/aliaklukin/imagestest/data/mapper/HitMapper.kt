package com.aliaklukin.imagestest.data.mapper

import com.aliaklukin.imagestest.data.model.HitResponse
import com.aliaklukin.imagestest.domain.model.Hit

class HitMapper {
    fun toHit(response: HitResponse?): Hit {
        return Hit(
            id = response?.id ?: 0,
            pageURL = response?.pageURL ?: "",
            type = response?.type ?: "",
            tags = response?.tags ?: "",
            previewURL = response?.previewURL ?: "",
            webFormatURL = response?.webFormatURL ?: "",
            largeImageURL = response?.largeImageURL ?: "",
            imageSize = response?.imageSize ?: 0,
            views = response?.views ?: 0,
            downloads = response?.downloads ?: 0,
            likes = response?.likes ?: 0,
            comments = response?.comments ?: 0,
            user = response?.user ?: "",
            userImageURL = response?.userImageURL ?: ""
        )
    }
}