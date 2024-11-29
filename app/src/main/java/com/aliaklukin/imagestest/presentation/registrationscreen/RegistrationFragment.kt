package com.aliaklukin.imagestest.presentation.registrationscreen

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.aliaklukin.imagestest.R
import com.aliaklukin.imagestest.databinding.FragmentRegistrationBinding
import com.aliaklukin.imagestest.presentation.base.AuthBaseFragment
import com.aliaklukin.imagestest.presentation.base.AuthViewModel
import com.aliaklukin.imagestest.presentation.utils.hideKeyboard
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment: AuthBaseFragment<FragmentRegistrationBinding>() {

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
            doOnTextChanged(
                ageET,
                ageTF,
                viewModel.state.value.ageError
            )

            handleFocusChange(emailET) { email ->
                viewModel.isEmailValid(email)
            }
            handleFocusChange(passwordET) { password ->
                viewModel.isPasswordValid(password)
            }
            handleFocusChange(ageET) { age ->
                viewModel.isAgeValid(age)
            }


            handleEditorAction(emailET, EditorInfo.IME_ACTION_NEXT) {
                passwordET.requestFocus()
            }
            handleEditorAction(passwordET, EditorInfo.IME_ACTION_NEXT) {
                ageET.requestFocus()
            }
            handleEditorAction(ageET, EditorInfo.IME_ACTION_DONE) {
                requireContext().hideKeyboard(ageET)
                ageET.clearFocus()
            }

            registerB.setOnClickListener {
                val email = emailET.text.toString()
                val password = passwordET.text.toString()
                val age = ageET.text.toString()
                val isEmailValid = viewModel.isEmailValid(email)
                val isPasswordValid = viewModel.isPasswordValid(password)
                val isAgeValid = viewModel.isAgeValid(age)
                if (!isEmailValid || !isPasswordValid || !isAgeValid) {
                    return@setOnClickListener
                }
                viewModel.register(email, password, age)
            }
        }
    }

    private fun handleUiState(uiState: AuthViewModel.AuthScreenState) {
        binding.run {
            emailTF.error = uiState.emailError
            passwordTF.error = uiState.passwordError
            ageTF.error = uiState.ageError
            if (uiState.isAuthCompleted) {
                findNavController().navigate(R.id.action_register_to_home)
            }
        }
    }
}