package com.part3.mediasearch.di

import com.part3.mediasearch.data.repository.SearchRepositoryImpl
import com.part3.mediasearch.data.resource.remote.SearchRemoteDataSource
import com.part3.mediasearch.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SearchRepositoryModule {
    @Provides
    @ViewModelScoped
    fun providesSearchRepository(remoteDataSource: SearchRemoteDataSource): SearchRepository =
        SearchRepositoryImpl(remoteDataSource)
}