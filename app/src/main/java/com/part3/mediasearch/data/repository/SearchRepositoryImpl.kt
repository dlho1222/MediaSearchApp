package com.part3.mediasearch.data.repository

import com.part3.mediasearch.data.resource.remote.SearchRemoteDataSource
import com.part3.mediasearch.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val remoteDateSource: SearchRemoteDataSource) :
    SearchRepository {

}