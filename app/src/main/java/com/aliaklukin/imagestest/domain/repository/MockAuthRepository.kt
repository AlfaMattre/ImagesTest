package com.aliaklukin.imagestest.domain.repository

import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.AuthInfo

interface MockAuthRepository {
    suspend fun login(email: String, password: String): ApiResult<AuthInfo>
    suspend fun register(email: String, password: String, age: String): ApiResult<AuthInfo>
}