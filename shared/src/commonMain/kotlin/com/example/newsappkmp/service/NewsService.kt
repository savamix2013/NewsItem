package com.example.newsappkmp.service

import com.example.newsappkmp.data.NewsItemsList
import com.example.newsappkmp.network.NetworkClient
import com.example.newsappkmp.network.NetworkConfiguration

class NewsService(
    private val httpClient: NetworkClient = NetworkClient()
) {

    suspend fun loadNews(): Result<NewsItemsList> {
        val url = "everything?q=science&apiKey=${NetworkConfiguration.apiKey}"

        return httpClient.request<NewsItemsList>(url)
    }
}
