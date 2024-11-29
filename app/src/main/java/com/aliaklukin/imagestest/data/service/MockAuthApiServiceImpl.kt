package com.aliaklukin.imagestest.data.service

import com.aliaklukin.imagestest.data.model.AuthInfoResponse
import com.aliaklukin.imagestest.domain.model.LoginRequest
import com.aliaklukin.imagestest.domain.model.RegisterRequest
import com.aliaklukin.imagestest.presentation.utils.checkAge
import com.aliaklukin.imagestest.presentation.utils.checkEmail
import com.aliaklukin.imagestest.presentation.utils.checkPassword
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.Response

class MockAuthApiServiceImpl : MockAuthApiService {

    override suspend fun login(loginRequest: LoginRequest): Response<AuthInfoResponse> {
        delay(2000)
        return if (loginRequest.email.checkEmail() && loginRequest.password.checkPassword()) {
            Response.success(
                AuthInfoResponse(
                    token = "sample-login-token",
                    message = "Login successful"
                )
            )
        } else {
            Response.error(
                401,
                ResponseBody.create(
                    "application/json".toMediaTypeOrNull(),
                    """{"detail": "Invalid email or password"}"""
                )
            )
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): Response<AuthInfoResponse> {
        return if (
            registerRequest.email.checkEmail() &&
            registerRequest.password.checkPassword() &&
            registerRequest.age.checkAge()
        ) {
            Response.success(
                AuthInfoResponse(
                    token = "sample-register-token",
                    message = "Registration successful"
                )
            )
        } else {
            Response.error(
                400,
                ResponseBody.create(
                    "application/json".toMediaTypeOrNull(),
                    """{"detail": "Invalid email or password"}"""
                )
            )
        }
    }
}