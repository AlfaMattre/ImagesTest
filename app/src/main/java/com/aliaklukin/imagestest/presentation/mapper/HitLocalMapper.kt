package com.aliaklukin.imagestest.presentation.mapper

import com.aliaklukin.imagestest.domain.model.Hit
import com.aliaklukin.imagestest.presentation.model.HitLocal

class HitLocalMapper {
    fun toHitLocal(hit: Hit): HitLocal =
        with(hit) {
            HitLocal(
                id = id,
                pageURL = pageURL,
                type = type,
                tags = tags,
                previewURL = previewURL,
                webFormatURL = webFormatURL,
                largeImageURL = largeImageURL,
                imageSize = imageSize,
                views = views,
                downloads = downloads,
                likes = likes,
                comments = comments,
                user = user,
                userImageURL = userImageURL
            )
        }
}