package com.aliaklukin.imagestest.domain.repository

import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.Hits

interface NetworkRepository {
    suspend fun getHits(page: Int): ApiResult<Hits>
}