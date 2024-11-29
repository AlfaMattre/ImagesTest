package com.aliaklukin.imagestest.data.mapper

import com.aliaklukin.imagestest.data.model.HitsResponse
import com.aliaklukin.imagestest.domain.model.Hits
import javax.inject.Inject

class HitsMapper @Inject constructor(
    private val hitMapper: HitMapper
) {
    fun toHits(response: HitsResponse?): Hits {
        return Hits(
            hits = response?.hits?.map { hitMapper.toHit(it) } ?: emptyList()
        )
    }
}