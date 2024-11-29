package com.aliaklukin.imagestest.data.mapper

import com.aliaklukin.imagestest.data.model.AuthInfoResponse
import com.aliaklukin.imagestest.domain.model.AuthInfo

class AuthInfoMapper {
    fun toAuthInfo(response: AuthInfoResponse?): AuthInfo =
        AuthInfo(
            token = response?.token ?: "",
            message = response?.message ?: ""
        )
}