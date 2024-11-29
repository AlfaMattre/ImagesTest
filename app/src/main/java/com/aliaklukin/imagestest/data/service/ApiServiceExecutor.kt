package com.aliaklukin.imagestest.data.service

import com.aliaklukin.imagestest.data.mapper.AuthInfoMapper
import com.aliaklukin.imagestest.data.mapper.HitsMapper
import com.aliaklukin.imagestest.data.model.AuthInfoResponse
import com.aliaklukin.imagestest.data.model.HitsResponse
import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.AuthInfo
import com.aliaklukin.imagestest.domain.model.Hits
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class ApiServiceExecutor @Inject constructor(
    private val hitsMapper: HitsMapper,
    private val authInfoMapper: AuthInfoMapper
) {
    suspend fun getImages(response: Response<HitsResponse>): ApiResult<Hits> =
        processResponse(response) { body ->
            hitsMapper.toHits(body)
        }

    suspend fun login(response: Response<AuthInfoResponse>): ApiResult<AuthInfo> =
        processResponse(response) { body ->
            authInfoMapper.toAuthInfo(body)
        }

    suspend fun register(response: Response<AuthInfoResponse>): ApiResult<AuthInfo> =
        processResponse(response) { body ->
            authInfoMapper.toAuthInfo(body)
        }

    private suspend fun <T, R> processResponse(
        response: Response<T>,
        successMapper: (T?) -> R
    ): ApiResult<R> = withContext(Dispatchers.IO) {
        try {
            if (response.isSuccessful) {
                return@withContext ApiResult.Success(
                    successMapper(response.body())
                )
            } else {
                return@withContext ApiResult.Error(
                    Throwable(
                        getErrorMessage(response.errorBody())
                    )
                )
            }
        } catch (e: Exception) {
            return@withContext ApiResult.Error(e)
        }
    }

    private fun getErrorMessage(errorBody: ResponseBody?): String? {
        val jsonObject = errorBody?.charStream()?.readText()
            ?.let { JSONObject(it) }
        return jsonObject?.getString("detail")
    }
}