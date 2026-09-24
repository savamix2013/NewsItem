package com.example.newsappkmp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsItemsList(
    val status: String? = null,
    val totalResults: Int? = null,
    @SerialName("articles") val articles: List<NewsItem>? = null
)