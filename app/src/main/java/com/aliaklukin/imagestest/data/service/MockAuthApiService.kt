package com.aliaklukin.imagestest.data.service

import com.aliaklukin.imagestest.data.model.AuthInfoResponse
import com.aliaklukin.imagestest.domain.model.LoginRequest
import com.aliaklukin.imagestest.domain.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MockAuthApiService {

    @POST("api/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<AuthInfoResponse>

    @POST("api/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<AuthInfoResponse>
}