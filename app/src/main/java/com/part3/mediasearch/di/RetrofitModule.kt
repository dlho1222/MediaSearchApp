package com.part3.mediasearch.di

import com.google.gson.GsonBuilder
import com.part3.mediasearch.util.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient.Builder,
        converterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/v2/search/")
            .client(client.build())
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    @Singleton
    fun providesOkHttpClient(apiKey:String): OkHttpClient.Builder
    = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor(apiKey))
        .connectTimeout(5,TimeUnit.SECONDS)
        .readTimeout(5,TimeUnit.SECONDS)
        .writeTimeout(5,TimeUnit.SECONDS)

    @Provides
    @Singleton
    fun providesConverterFactory():GsonConverterFactory=
        GsonConverterFactory.create(GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T':HH:mm:ss.SSSXXX")
            .create())
}