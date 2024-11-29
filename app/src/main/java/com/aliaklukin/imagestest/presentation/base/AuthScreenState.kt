package com.aliaklukin.imagestest.presentation.base

abstract class AuthScreenState {
    open val emailError: String? = null
    open val passwordError: String? = null
}