package com.part3.mediasearch.presentation.model

import java.util.Date

data class ImageItem(
    val collection: String?,
    override val thumbnailUrl: String?,
    val imageUrl: String?,
    val width: Int?,
    val height: Int?,
    val displaySiteName: String?,
    val docUrl: String?,
    override val dateTime: Date?,
    override val isFavorite: Boolean
) : SearchItem {
    override val viewType: ViewType
        get() = ViewType.IMAGE
}