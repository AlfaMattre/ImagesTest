package com.aliaklukin.imagestest.data.datasource

import com.aliaklukin.imagestest.data.service.ApiService
import com.aliaklukin.imagestest.data.service.ApiServiceExecutor
import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.Hits
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiServiceExecutor: ApiServiceExecutor
): NetworkDataSource {

    override suspend fun getHits(page: Int): ApiResult<Hits> {
        return apiServiceExecutor.getImages(apiService.getHits(page = page))
    }
}