package com.part3.mediasearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.part3.mediasearch.domain.model.DocumentEntity
import com.part3.mediasearch.domain.model.SearchEntity
import com.part3.mediasearch.domain.usecase.GetSearchImageUseCase
import com.part3.mediasearch.domain.usecase.GetSearchVideoUseCase
import com.part3.mediasearch.presentation.model.ImageItem
import com.part3.mediasearch.presentation.model.SearchItem
import com.part3.mediasearch.presentation.model.VideoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchVideoUseCase: GetSearchVideoUseCase,
    private val getSearchImageUseCase: GetSearchImageUseCase
) : ViewModel() {
    private val searchFlow = MutableSharedFlow<String>()
    private val _uiState = MutableStateFlow(SearchUiState.init())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            searchFlow.debounce(500)
                .collectLatest { query ->
                    loadingState(true)
                    runCatching {
                        val list =
                            createItems(getSearchVideoUseCase(query), getSearchImageUseCase(query))
                        _uiState.update { prevState ->
                            prevState.copy(list = list, isLoading = false)
                        }
                    }.onFailure {
                        loadingState(false)
                    }
                }
        }
    }


    fun search(query: String) {
        viewModelScope.launch {
            searchFlow.emit(query)
        }
    }

    private fun createItems(
        video: SearchEntity<DocumentEntity.VideoDocumentEntity>,
        image: SearchEntity<DocumentEntity.ImageDocumentEntity>
    ): List<SearchItem> {
        fun createVideoItem(video: SearchEntity<DocumentEntity.VideoDocumentEntity>): List<SearchItem> =
            video.documents?.map { item ->
                VideoItem(
                    title = item.title,
                    url = item.url,
                    dateTime = item.datetime,
                    playTime = item.playTime,
                    thumbnailUrl = item.thumbnail,
                    author = item.author,
                    isFavorite = false
                )
            }.orEmpty()

        fun createImageItem(image: SearchEntity<DocumentEntity.ImageDocumentEntity>): List<SearchItem> =
            image.documents?.map { item ->
                ImageItem(
                    collection = item.collection,
                    thumbnailUrl = item.thumbnailUrl,
                    imageUrl = item.imageUrl,
                    width = item.width,
                    height = item.height,
                    displaySiteName = item.displaySiteName,
                    docUrl = item.docUrl,
                    dateTime = item.datTime,
                    isFavorite = false
                )
            }.orEmpty()

        return arrayListOf<SearchItem>().apply {
            addAll(createVideoItem(video))
            addAll(createImageItem(image))
            sortedByDescending { it.dateTime }
        }
    }

    private fun loadingState(isLoading: Boolean) {
        _uiState.update { prevState ->
            prevState.copy(isLoading = isLoading)
        }
    }
}