package com.aliaklukin.imagestest.presentation.base

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.viewbinding.ViewBinding
import com.google.android.material.textfield.TextInputLayout

abstract class AuthBaseFragment<T : ViewBinding> : BaseFragment<T>() {

    protected fun doOnTextChanged(
        editText: EditText,
        textInputLayout: TextInputLayout,
        error: String?
    ) {
        editText.doOnTextChanged { _, _, _, _ ->
            if (error == null) {
                textInputLayout.error = null
            }
        }
    }

    protected fun handleFocusChange(editText: EditText, validation: (String) -> Unit) {
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validation(editText.text.toString())
            }
        }
    }

    protected fun handleEditorAction(editText: EditText, action: Int, onAction: () -> Unit) {
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == action) {
                onAction()
                true
            } else {
                false
            }
        }
    }
}