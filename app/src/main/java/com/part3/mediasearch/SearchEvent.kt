package com.part3.mediasearch

import com.part3.mediasearch.presentation.model.SearchItem

sealed interface SearchEvent {
    data class OnClickFavorite(val item: SearchItem) : SearchEvent
}