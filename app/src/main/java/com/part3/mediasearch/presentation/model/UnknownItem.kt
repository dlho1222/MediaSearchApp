package com.part3.mediasearch.presentation.model

import java.util.Date

data class UnknownItem(
    override val isFavorite: Boolean,
    override val dateTime: Date?,
    override val thumbnailUrl: String?
) : SearchItem {
    override val viewType: ViewType
        get() = ViewType.UNKNOWN
}