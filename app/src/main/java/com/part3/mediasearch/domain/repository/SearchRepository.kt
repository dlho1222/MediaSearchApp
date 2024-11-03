package com.part3.mediasearch.domain.repository

import com.part3.mediasearch.domain.model.DocumentEntity
import com.part3.mediasearch.domain.model.SearchEntity

interface SearchRepository {
    suspend fun searchVideos(query: String): SearchEntity<DocumentEntity.VideoDocumentEntity>

    suspend fun searchImages(query: String): SearchEntity<DocumentEntity.ImageDocumentEntity>
}