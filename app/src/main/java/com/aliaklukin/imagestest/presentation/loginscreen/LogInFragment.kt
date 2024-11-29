package com.aliaklukin.imagestest.presentation.loginscreen

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.aliaklukin.imagestest.R
import com.aliaklukin.imagestest.databinding.FragmentLoginBinding
import com.aliaklukin.imagestest.presentation.base.AuthBaseFragment
import com.aliaklukin.imagestest.presentation.base.AuthViewModel
import com.aliaklukin.imagestest.presentation.utils.hideKeyboard
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : AuthBaseFragment<FragmentLoginBinding>() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { uiState ->
                    handleUiState(uiState)
                }
            }
        }

        initUI()
    }

    private fun initUI() {
        binding.run {
            passwordTF.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE

            setTestInfo(emailET, passwordET)

            doOnTextChanged(
                emailET,
                emailTF,
                viewModel.state.value.emailError
            )
            doOnTextChanged(
                passwordET,
                passwordTF,
                viewModel.state.value.passwordError
            )

            handleFocusChange(emailET) { email ->
                viewModel.isEmailValid(email)
            }

            handleFocusChange(passwordET) { password ->
                viewModel.isPasswordValid(password)
            }


            handleEditorAction(emailET, EditorInfo.IME_ACTION_NEXT) {
                passwordET.requestFocus()
            }

            handleEditorAction(passwordET, EditorInfo.IME_ACTION_DONE) {
                requireContext().hideKeyboard(passwordET)
                passwordET.clearFocus()
            }

            loginB.setOnClickListener {
                val email = emailET.text.toString()
                val password = passwordET.text.toString()
                val isEmailValid = viewModel.isEmailValid(email)
                val isPasswordValid = viewModel.isPasswordValid(password)
                if (!isEmailValid || !isPasswordValid) {
                    return@setOnClickListener
                }
                viewModel.login(email, password)
            }

            registerTV.setOnClickListener {
                findNavController().navigate(R.id.action_login_to_register)
            }
        }
    }

    private fun handleUiState(uiState: AuthViewModel.AuthScreenState) {
        binding.run {
            emailTF.error = uiState.emailError
            passwordTF.error = uiState.passwordError
            if (uiState.isAuthCompleted) {
                findNavController().navigate(R.id.action_login_to_home)
            }
        }
    }
}