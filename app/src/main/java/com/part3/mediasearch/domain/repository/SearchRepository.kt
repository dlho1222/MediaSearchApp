package com.part3.mediasearch.domain.repository

interface SearchRepository {
    suspend fun searchVideos(query:String)

    suspend fun searchImages(query: String)
}