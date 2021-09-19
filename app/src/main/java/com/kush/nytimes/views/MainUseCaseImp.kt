package com.kush.nytimes.views

import com.kush.nytimes.networking.ApiRepository
import com.kush.nytimes.networking.Result
import com.kush.nytimes.networking.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MainUseCaseImp(
    private val service: ApiRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MainUseCase {

    override suspend fun getViewedArticlesCall(): Result<ViewArticleResponse> {
        return safeApiCall(dispatcher) {
            service.getViewedArticlesApi()
        }
    }
}