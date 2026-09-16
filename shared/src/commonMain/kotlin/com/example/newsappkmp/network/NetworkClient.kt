package com.example.newsappkmp.network

import com.example.newsappkmp.data.NewsItemsList

class NetworkClient(
    private val networkConfiguration: NetworkConfiguration
) {
    suspend fun request(url: String): Result<NewsItemsList> {
        TODO("Provide the return value")
    }
}