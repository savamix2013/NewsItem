package com.example.newsappkmp.service

import com.example.newsappkmp.data.NewsItemsList
import com.example.newsappkmp.network.NetworkClient

import com.example.newsappkmp.network.NetworkConfiguration

class NewsService(
    private val httpClient: NetworkClient = NetworkClient()
) {
    suspend fun loadNews(): Result<NewsItemsList> {
        return httpClient.request<NewsItemsList>(URL)
    }

    companion object {
        // Тестовий URL для запиту новин за темою science
        val URL = "everything?q=science&apiKey=${NetworkConfiguration.API_KEY}"
    }
}