package com.part3.mediasearch.data.repository

import com.part3.mediasearch.data.resource.remote.SearchRemoteDataSource
import com.part3.mediasearch.domain.model.DocumentEntity
import com.part3.mediasearch.domain.model.SearchEntity
import com.part3.mediasearch.domain.model.toImageEntity
import com.part3.mediasearch.domain.model.toVideoEntity
import com.part3.mediasearch.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val remoteDateSource: SearchRemoteDataSource) :
    SearchRepository {
    override suspend fun searchVideos(query: String): SearchEntity<DocumentEntity.VideoDocumentEntity> =
        remoteDateSource.searchVideos(query).toVideoEntity()

    override suspend fun searchImages(query: String): SearchEntity<DocumentEntity.ImageDocumentEntity> =
        remoteDateSource.searchImages(query).toImageEntity()

}