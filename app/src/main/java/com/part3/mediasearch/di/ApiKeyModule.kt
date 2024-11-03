package com.part3.mediasearch.di

import android.content.Context
import com.part3.mediasearch.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiKeyModule {
    @Provides
    @Singleton
    fun providesApiKey(@ApplicationContext context: Context): String =
        context.getString(R.string.apiKey)
}