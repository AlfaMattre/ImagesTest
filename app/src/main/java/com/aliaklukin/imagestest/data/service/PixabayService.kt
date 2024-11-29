package com.aliaklukin.imagestest.data.service

import com.aliaklukin.imagestest.data.model.HitsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {

    @GET("api/")
    suspend fun getHits(
        @Query("image_type") type: String = "all",
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int
    ): Response<HitsResponse>
}