package com.kush.nytimes.views

data class ViewArticles(
    val uri: String? = null,
    val url: String? = null,
    val id: Long? = null,
    val asset_id: Long? = null,
    val source: String? = null,
    val published_date: String? = null,
    val updated: String? = null,
    val section: String? = null,
    val subsection: String? = null,
    val nytdsection: String? = null,
    val adx_keywords: String? = null,
    val byline: String? = null,
    val type: String? = null,
    val title: String? = null,
    val abstract: String? = null,
)

data class ViewArticleResponse(
    val status: String? = null,
    val num_results: Int? = null,
    val results: List<ViewArticles>? = null
)