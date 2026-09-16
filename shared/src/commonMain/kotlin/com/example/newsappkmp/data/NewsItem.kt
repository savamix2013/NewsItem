package com.example.newsappkmp.data

// Основна модель новини
data class NewsItem(
    val source: Source? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
    val urlToImage: String? = null
)
