package com.aliaklukin.imagestest.data.datasource

import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.Hits

interface NetworkDataSource {
    suspend fun getHits(page: Int): ApiResult<Hits>
}