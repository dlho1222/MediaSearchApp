package com.part3.mediasearch.presentation.model

import java.util.Date

data class VideoItem(
    val title: String?,
    val url: String?,
    override val dateTime: Date?,
    val playTime: Int?,
    override val thumbnailUrl: String?,
    val author: String?,
    override val isFavorite: Boolean,
) : SearchItem {
    override val viewType: ViewType
        get() = ViewType.VIDEO
}
