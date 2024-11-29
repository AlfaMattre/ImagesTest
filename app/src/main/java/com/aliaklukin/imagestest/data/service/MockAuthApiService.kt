package com.aliaklukin.imagestest.data.service

import com.aliaklukin.imagestest.data.model.AuthInfoResponse
import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.presentation.utils.checkAge
import com.aliaklukin.imagestest.presentation.utils.checkEmail
import com.aliaklukin.imagestest.presentation.utils.checkPassword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MockAuthApiService {

    suspend fun login(email: String, password: String): ApiResult<AuthInfoResponse> = withContext(
        Dispatchers.IO
    ) {
        delay(2000)
        try {
            if (email.checkEmail() && password.checkPassword()) {
                ApiResult.Success(
                    AuthInfoResponse(
                        token = "sample-login-token",
                        message = "Login successful"
                    )
                )
            } else {
                ApiResult.Error(Throwable("Invalid email or password"))
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    suspend fun register(email: String, password: String, age: String): ApiResult<AuthInfoResponse> =
        withContext(Dispatchers.IO) {
            delay(2000)
            try {
                if (email.checkEmail() && password.checkPassword() && age.checkAge()) {
                    ApiResult.Success(
                        AuthInfoResponse(
                            token = "sample-register-token",
                            message = "Registration successful"
                        )
                    )
                } else {
                    ApiResult.Error(Throwable("Invalid registration details"))
                }
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
}