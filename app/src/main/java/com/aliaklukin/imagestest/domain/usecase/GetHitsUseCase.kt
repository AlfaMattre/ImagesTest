package com.aliaklukin.imagestest.domain.usecase

import com.aliaklukin.imagestest.domain.model.ApiResult
import com.aliaklukin.imagestest.domain.model.Hits
import com.aliaklukin.imagestest.domain.repository.NetworkRepository
import javax.inject.Inject

class GetHitsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    suspend fun execute(page: Int): ApiResult<Hits> = networkRepository.getHits(page)
}