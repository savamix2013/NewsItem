package com.example.newsappkmp.viewmodel

import com.example.newsappkmp.network.NetworkClient
import com.example.newsappkmp.network.NetworkConfiguration
import com.example.newsappkmp.service.NewsService

class NewsViewModel : BaseViewModel() {
    private val service = NewsService(
        httpClient = NetworkClient(NetworkConfiguration())
    )

    fun loadNews() {
        // Логіку завантаження підключимо в наступних розділах
    }
}