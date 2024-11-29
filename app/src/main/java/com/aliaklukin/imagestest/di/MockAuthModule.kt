package com.aliaklukin.imagestest.di

import com.aliaklukin.imagestest.data.repository.MockAuthRepositoryImpl
import com.aliaklukin.imagestest.data.service.MockAuthApiService
import com.aliaklukin.imagestest.domain.repository.MockAuthRepository
import com.aliaklukin.imagestest.domain.usecase.LoginUseCase
import com.aliaklukin.imagestest.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MockAuthModule {

    @Provides
    @Singleton
    fun provideMockAuthApiService(): MockAuthApiService {
        return MockAuthApiService()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        mockAuthApiService: MockAuthApiService
    ): MockAuthRepository {
        return MockAuthRepositoryImpl(mockAuthApiService)
    }

    @Provides
    fun provideLoginUseCase(
        mockAuthRepository: MockAuthRepository
    ): LoginUseCase = LoginUseCase(mockAuthRepository)

    @Provides
    fun provideRegisterUseCase(
        mockAuthRepository: MockAuthRepository
    ): RegisterUseCase = RegisterUseCase(mockAuthRepository)
}