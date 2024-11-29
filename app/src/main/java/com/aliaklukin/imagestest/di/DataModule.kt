package com.aliaklukin.imagestest.di

import com.aliaklukin.imagestest.BuildConfig
import com.aliaklukin.imagestest.data.datasource.NetworkDataSource
import com.aliaklukin.imagestest.data.datasource.NetworkDataSourceImpl
import com.aliaklukin.imagestest.data.mapper.AuthInfoMapper
import com.aliaklukin.imagestest.data.mapper.HitMapper
import com.aliaklukin.imagestest.data.mapper.HitsMapper
import com.aliaklukin.imagestest.data.repository.MockAuthRepositoryImpl
import com.aliaklukin.imagestest.data.repository.NetworkRepositoryImpl
import com.aliaklukin.imagestest.data.service.ApiKeyInterceptor
import com.aliaklukin.imagestest.data.service.ApiServiceExecutor
import com.aliaklukin.imagestest.data.service.MockAuthApiService
import com.aliaklukin.imagestest.data.service.MockAuthApiServiceImpl
import com.aliaklukin.imagestest.data.service.PixabayService
import com.aliaklukin.imagestest.domain.repository.MockAuthRepository
import com.aliaklukin.imagestest.domain.repository.NetworkRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideApiKeyInterceptor(): ApiKeyInterceptor = ApiKeyInterceptor()

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(200, TimeUnit.SECONDS)
            .writeTimeout(200, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): PixabayService {
        return retrofit.create(PixabayService::class.java)
    }

    @Provides
    fun provideMockAuthRepository(
        networkDataSource: NetworkDataSource
    ): MockAuthRepository {
        return MockAuthRepositoryImpl(networkDataSource)
    }

    @Provides
    fun provideMockAuthApiService(): MockAuthApiService = MockAuthApiServiceImpl()

    @Provides
    fun provideNetworkDataSource(
        pixabayService: PixabayService,
        mockAuthApiService: MockAuthApiService,
        apiServiceExecutor: ApiServiceExecutor
    ): NetworkDataSource {
        return NetworkDataSourceImpl(
            pixabayService, mockAuthApiService, apiServiceExecutor
        )
    }

    @Provides
    fun provideNetworkRepository(
        networkDataSource: NetworkDataSource
    ): NetworkRepository {
        return NetworkRepositoryImpl(
            networkDataSource
        )
    }

    @Provides
    fun provideApiServiceExecutor(
        hitsMapper: HitsMapper,
        authInfoMapper: AuthInfoMapper
    ): ApiServiceExecutor {
        return ApiServiceExecutor(
            hitsMapper,
            authInfoMapper
        )
    }

    @Provides
    fun provideHitMapper(): HitMapper = HitMapper()

    @Provides
    fun provideHitsMapper(
        hitMapper: HitMapper
    ): HitsMapper = HitsMapper(hitMapper)

    @Provides
    fun provideAuthInfoMapper(): AuthInfoMapper = AuthInfoMapper()
}