package com.aliaklukin.imagestest.data.repository

import com.aliaklukin.imagestest.data.datasource.NetworkDataSource
import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.AuthInfo
import com.aliaklukin.imagestest.domain.repository.MockAuthRepository
import javax.inject.Inject

class MockAuthRepositoryImpl @Inject constructor(
    private val dataSource: NetworkDataSource
) : MockAuthRepository {

    override suspend fun login(email: String, password: String): ApiResult<AuthInfo> {
        return dataSource.login(email, password)
    }

    override suspend fun register(
        email: String,
        password: String,
        age: String
    ): ApiResult<AuthInfo> {
        return dataSource.register(email, password, age)
    }
}