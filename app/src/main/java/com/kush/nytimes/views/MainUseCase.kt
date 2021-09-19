package com.kush.nytimes.views

import com.kush.nytimes.networking.Result

interface MainUseCase {

    suspend fun getViewedArticlesCall(
    ): Result<ViewArticleResponse>
}