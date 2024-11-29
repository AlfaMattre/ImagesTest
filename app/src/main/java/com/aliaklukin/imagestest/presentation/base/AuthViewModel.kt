package com.aliaklukin.imagestest.presentation.base

import androidx.lifecycle.viewModelScope
import com.aliaklukin.imagestest.domain.usecase.LoginUseCase
import com.aliaklukin.imagestest.domain.usecase.RegisterUseCase
import com.aliaklukin.imagestest.presentation.utils.LoginErrors
import com.aliaklukin.imagestest.presentation.utils.checkAge
import com.aliaklukin.imagestest.presentation.utils.checkEmail
import com.aliaklukin.imagestest.presentation.utils.checkPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {

    data class AuthScreenState(
        val emailError: String? = null,
        val passwordError: String? = null,
        val ageError: String? = null,
        val isAuthCompleted: Boolean = false
    )

    private val _state = MutableStateFlow(AuthScreenState())
    val state: StateFlow<AuthScreenState> get() = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = getDataByUseCase(loginUseCase.execute(email, password))
            result?.let { authInfo ->
                _state.update {
                    it.copy(
                        isAuthCompleted = authInfo.token.isNotEmpty()
                    )
                }
            }
        }
    }

    fun register(email: String, password: String, age: String) {
        viewModelScope.launch {
            val result = getDataByUseCase(registerUseCase.execute(email, password, age))
            result?.let { authInfo ->
                _state.update {
                    it.copy(
                        isAuthCompleted = authInfo.token.isNotEmpty()
                    )
                }
            }
        }
    }

    fun isEmailValid(email: String): Boolean {
        return when {
            email.isEmpty() -> {
                _state.update { it.copy(emailError = LoginErrors.EMAIL_EMPTY.error) }
                false
            }
            !email.checkEmail() -> {
                _state.update { it.copy(emailError = LoginErrors.EMAIL_INVALID.error) }
                false
            }
            else -> {
                _state.update { it.copy(emailError = null) }
                true
            }
        }
    }

    fun isPasswordValid(password: String): Boolean {
        return when {
            password.isEmpty() -> {
                _state.update { it.copy(passwordError = LoginErrors.PASSWORD_EMPTY.error) }
                false
            }
            !password.checkPassword() -> {
                _state.update { it.copy(passwordError = LoginErrors.PASSWORD_INVALID.error) }
                false
            }
            else -> {
                _state.update { it.copy(passwordError = null) }
                true
            }
        }
    }

    fun isAgeValid(age: String): Boolean {
        return when {
            age.isEmpty() -> {
                _state.update { it.copy(ageError = LoginErrors.AGE_EMPTY.error) }
                false
            }
            !age.checkAge() -> {
                _state.update { it.copy(ageError = LoginErrors.AGE_INVALID.error) }
                false
            }
            else -> {
                _state.update { it.copy(ageError = null) }
                true
            }
        }
    }
}