package com.kush.nytimes.views

import com.kush.nytimes.networking.Result

interface MainApiRepo {

    suspend fun getViewedArticlesCall(
    ): Result<ViewArticleResponse>
}