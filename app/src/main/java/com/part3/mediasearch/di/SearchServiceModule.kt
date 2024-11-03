package com.part3.mediasearch.di

import com.part3.mediasearch.data.resource.remote.SearchRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchServiceModule {
    @Provides
    @Singleton
    fun providesSearchService(retrofit: Retrofit): SearchRemoteDataSource =
        retrofit.create(SearchRemoteDataSource::class.java)
}