package com.aliaklukin.imagestest.data.repository

import com.aliaklukin.imagestest.data.model.AuthInfoResponse
import com.aliaklukin.imagestest.data.service.MockAuthApiService
import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.repository.MockAuthRepository
import javax.inject.Inject

class MockAuthRepositoryImpl @Inject constructor(
    private val authApiService: MockAuthApiService
) : MockAuthRepository {

    override suspend fun login(email: String, password: String): ApiResult<AuthInfoResponse> {
        return authApiService.login(email, password)
    }

    override suspend fun register(
        email: String,
        password: String,
        age: String
    ): ApiResult<AuthInfoResponse> {
        return authApiService.register(email, password, age)
    }
}