package com.aliaklukin.imagestest.domain.usecase

import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.AuthInfo
import com.aliaklukin.imagestest.domain.repository.MockAuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: MockAuthRepository
) {
    suspend fun execute(email: String, password: String, age: String): ApiResult<AuthInfo> {
        return authRepository.register(email, password, age)
    }
}