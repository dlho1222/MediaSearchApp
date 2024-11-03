package com.part3.mediasearch.domain.model

import java.util.Date

data class SearchEntity<T>(
    val meta: MetaEntity?,
    val documents: List<T>?
)

data class MetaEntity(
    val totalCount: Int?,
    val pageableCount: Int?,
    val isEnd: Boolean?
)

sealed class DocumentEntity() {
    data class VideoDocumentEntity(
        val title: String?,
        val url: String?,
        val datetime: Date?,
        val playTime: Int?,
        val thumbnail: String?,
        val author: String?,
    ) : DocumentEntity()

    data class ImageDocumentEntity(
        val collection: String?,
        val thumbnailUrl: String?,
        val imageUrl: String?,
        val width: Int?,
        val height: Int?,
        val displaySiteName: String?,
        val docUrl: String?,
        val datTime: Date?,
    ) : DocumentEntity()
}
