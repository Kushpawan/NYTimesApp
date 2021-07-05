package com.kush.nytimes.networking

import com.kush.nytimes.views.ViewArticleResponse
import retrofit2.http.*

interface ApiRepository {

    @GET("viewed/7.json?api-key=UMCu0Ro0mpHCwoCBlG0Wjg5JOGRcGn0a")
    suspend fun getViewedArticlesApi(): ViewArticleResponse

}