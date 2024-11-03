package com.part3.mediasearch.domain.usecase

import com.part3.mediasearch.domain.model.DocumentEntity
import com.part3.mediasearch.domain.model.SearchEntity
import com.part3.mediasearch.domain.repository.SearchRepository
import javax.inject.Inject

class GetSearchImageUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend operator fun invoke(query: String): SearchEntity<DocumentEntity.ImageDocumentEntity> =
        repository.searchImages(query)
}