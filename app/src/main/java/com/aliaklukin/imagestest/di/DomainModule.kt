package com.aliaklukin.imagestest.di

import com.aliaklukin.imagestest.domain.repository.NetworkRepository
import com.aliaklukin.imagestest.domain.usecase.GetHitsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetHitsUseCase(
        networkRepository: NetworkRepository
    ): GetHitsUseCase = GetHitsUseCase(networkRepository)
}