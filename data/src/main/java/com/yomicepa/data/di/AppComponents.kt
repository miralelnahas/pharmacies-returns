package com.yomicepa.data.di

import com.google.gson.Gson
import com.yomicepa.data.BuildConfig
import com.yomicepa.data.network.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppComponents {
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        authorizationInterceptor: AuthorizationInterceptor
    ): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor).build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}