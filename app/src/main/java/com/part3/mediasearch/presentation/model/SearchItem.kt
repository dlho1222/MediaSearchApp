package com.part3.mediasearch.presentation.model

import java.util.Date

interface SearchItem {
    val thumbnailUrl: String?
    val dateTime: Date?
    val isFavorite: Boolean
    val viewType: ViewType
    fun getKey() = hashCode()
}

enum class ViewType {
    VIDEO,
    IMAGE,
    UNKNOWN
    ;
}