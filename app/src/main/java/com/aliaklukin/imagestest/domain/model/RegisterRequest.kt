package com.aliaklukin.imagestest.domain.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val age: String
)
