package com.aliaklukin.imagestest.domain.repository

import com.aliaklukin.imagestest.data.model.AuthInfoResponse
import com.aliaklukin.imagestest.domain.model.ApiResult

interface MockAuthRepository {
    suspend fun login(email: String, password: String): ApiResult<AuthInfoResponse>
    suspend fun register(email: String, password: String, age: String): ApiResult<AuthInfoResponse>
}