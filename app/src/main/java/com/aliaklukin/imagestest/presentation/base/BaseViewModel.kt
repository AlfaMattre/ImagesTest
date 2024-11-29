package com.aliaklukin.imagestest.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.aliaklukin.imagestest.domain.model.ApiResult

open class BaseViewModel: ViewModel() {
    protected fun <T> getDataByUseCase(result: ApiResult<T>): T? {
        return when (result) {
            is ApiResult.Error -> {
                try {
                    Log.e("Result: Error", result.exception.toString())
                } catch (e: Exception) {
                    Log.e("Result: Error", e.toString())
                }
                null
            }
            is ApiResult.Success -> {
                Log.i("Result: Success", "ReceivedData: ${result.data.toString()}")
                result.data
            }
        }
    }
}