package com.aliaklukin.imagestest.domain.usecase

import com.aliaklukin.imagestest.data.model.AuthInfoResponse
import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.repository.MockAuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val mockAuthRepository: MockAuthRepository
) {
    suspend fun execute(email: String, password: String): ApiResult<AuthInfoResponse> {
        return mockAuthRepository.login(email, password)
    }
}