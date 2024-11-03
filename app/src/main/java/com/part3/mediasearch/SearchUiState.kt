package com.part3.mediasearch

import com.part3.mediasearch.presentation.model.SearchItem

data class SearchUiState(
    val list: List<SearchItem>,
    val isLoading: Boolean
) {
    companion object {
        fun init() = SearchUiState(
            list = emptyList(),
            isLoading = false
        )
    }
}