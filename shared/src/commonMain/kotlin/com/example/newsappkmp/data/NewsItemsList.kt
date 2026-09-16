package com.example.newsappkmp.data

// Список новин
data class NewsItemsList(
    val totalResults: Int,
    val articles: List<NewsItem>
)
