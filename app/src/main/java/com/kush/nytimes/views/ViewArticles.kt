package com.kush.nytimes.views

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    val media: List<Media>? = null
) : Parcelable

@Parcelize
data class ViewArticleResponse(
    val status: String? = null,
    val num_results: Int? = null,
    val results: MutableList<ViewArticles>? = null
) : Parcelable


@Parcelize
data class Media(
    val type: String? = null,
    val caption: String? = null,
    val copyright: String? = null,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MetaData>? = null
) : Parcelable

@Parcelize
data class MetaData(
    val url: String? = null
) : Parcelable