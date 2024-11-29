package com.aliaklukin.imagestest.data.datasource

import com.aliaklukin.imagestest.data.service.ApiServiceExecutor
import com.aliaklukin.imagestest.data.service.MockAuthApiService
import com.aliaklukin.imagestest.data.service.PixabayService
import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.AuthInfo
import com.aliaklukin.imagestest.domain.model.Hits
import com.aliaklukin.imagestest.domain.model.LoginRequest
import com.aliaklukin.imagestest.domain.model.RegisterRequest
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val pixabayService: PixabayService,
    private val mockAuthApiService: MockAuthApiService,
    private val apiServiceExecutor: ApiServiceExecutor
) : NetworkDataSource {

    override suspend fun getHits(page: Int): ApiResult<Hits> {
        return apiServiceExecutor.getImages(pixabayService.getHits(page = page))
    }

    override suspend fun login(email: String, password: String): ApiResult<AuthInfo> {
        return apiServiceExecutor.login(mockAuthApiService.login(LoginRequest(email, password)))
    }

    override suspend fun register(
        email: String,
        password: String,
        age: String
    ): ApiResult<AuthInfo> {
        return apiServiceExecutor.register(
            mockAuthApiService.register(
                RegisterRequest(
                    email,
                    password,
                    age
                )
            )
        )
    }
}