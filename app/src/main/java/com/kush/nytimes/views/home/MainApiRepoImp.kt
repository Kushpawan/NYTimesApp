package com.kush.nytimes.views.home

import com.kush.nytimes.networking.ApiRepository
import com.kush.nytimes.networking.Result
import com.kush.nytimes.networking.safeApiCall
import com.kush.nytimes.views.ViewArticleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MainApiRepoImp(
    private val service: ApiRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MainApiRepo {

    override suspend fun getViewedArticlesCall(): Result<ViewArticleResponse> {
        return safeApiCall(dispatcher) {
            service.getViewedArticlesApi()
        }
    }
}