package com.aliaklukin.imagestest.data.datasource

import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.AuthInfo
import com.aliaklukin.imagestest.domain.model.Hits

interface NetworkDataSource {
    suspend fun getHits(page: Int): ApiResult<Hits>
    suspend fun login(email: String, password: String): ApiResult<AuthInfo>
    suspend fun register(email: String, password: String, age: String): ApiResult<AuthInfo>
}