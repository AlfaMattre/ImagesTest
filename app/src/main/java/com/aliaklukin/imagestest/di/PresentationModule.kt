package com.aliaklukin.imagestest.di

import com.aliaklukin.imagestest.presentation.mapper.HitLocalMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideHitLocalMapper() = HitLocalMapper()
}