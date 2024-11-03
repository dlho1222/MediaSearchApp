package com.part3.mediasearch.domain.model

import com.part3.mediasearch.data.model.ImageDocumentResponse
import com.part3.mediasearch.data.model.MetaResponse
import com.part3.mediasearch.data.model.SearchResponse
import com.part3.mediasearch.data.model.VideoDocumentResponse

fun MetaResponse.toEntity() = MetaEntity(
    totalCount = totalCount,
    pageableCount = pageableCount,
    isEnd = isEnd
)

fun SearchResponse<VideoDocumentResponse>.toVideoEntity() = SearchEntity(
    meta = meta?.toEntity(),
    documents = documents?.map { response -> response.toEntity() }
)

fun SearchResponse<ImageDocumentResponse>.toImageEntity() = SearchEntity(
    meta = meta?.toEntity(),
    documents = documents?.map { response -> response.toEntity() }
)

fun VideoDocumentResponse.toEntity() = DocumentEntity.VideoDocumentEntity(
    title = title,
    url = url,
    datetime = datetime,
    playTime = playTime,
    thumbnail = thumbnail,
    author = author
)

fun ImageDocumentResponse.toEntity() = DocumentEntity.ImageDocumentEntity(
    collection = collection,
    thumbnailUrl = thumbnailUrl,
    imageUrl = imageUrl,
    width = width,
    height = height,
    displaySiteName = displaySiteName,
    docUrl = docUrl,
    datTime = datTime
)
