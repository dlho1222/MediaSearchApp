package com.part3.mediasearch.data.resource.remote

import com.part3.mediasearch.data.model.ImageDocumentResponse
import com.part3.mediasearch.data.model.SearchResponse
import com.part3.mediasearch.data.model.VideoDocumentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteDataSource {
    @GET("vclip")
    suspend fun searchVideos(@Query("query") query: String): SearchResponse<VideoDocumentResponse>

    @GET("image")
    suspend fun searchImages(@Query("query") query: String): SearchResponse<ImageDocumentResponse>
}