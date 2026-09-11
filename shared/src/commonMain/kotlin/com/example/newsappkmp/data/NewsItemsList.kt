package com.example.newsappkmp.data

class NewsItemsList {
    // Список новин
    data class NewsItemsList(
        val totalResults: Int,
        val articles: List<NewsItem>
    )
}