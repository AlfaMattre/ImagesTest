package com.aliaklukin.imagestest.data.repository

import com.aliaklukin.imagestest.data.datasource.NetworkDataSource
import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.Hits
import com.aliaklukin.imagestest.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
): NetworkRepository {

    override suspend fun getHits(page: Int): ApiResult<Hits> {
        return networkDataSource.getHits(page)
    }
}