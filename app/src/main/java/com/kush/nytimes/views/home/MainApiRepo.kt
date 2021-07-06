package com.kush.nytimes.views.home

import com.kush.nytimes.networking.Result
import com.kush.nytimes.views.model.ViewArticleResponse

interface MainApiRepo {

    suspend fun getViewedArticlesCall(
    ): Result<ViewArticleResponse>
}