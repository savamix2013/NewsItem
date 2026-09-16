package com.example.newsappkmp.service

import com.example.newsappkmp.data.NewsItemsList
import com.example.newsappkmp.network.NetworkClient

class NewsService(private val httpClient: NetworkClient) {
    suspend fun loadNews(): Result<NewsItemsList> {
        return httpClient.request(URL)
    }

    companion object {
        const val URL = "https://newsapi.org/v2/everything?q=science"
    }
}