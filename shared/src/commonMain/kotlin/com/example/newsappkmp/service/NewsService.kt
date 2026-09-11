package com.example.newsappkmp.service

import com.example.newsappkmp.network.NetworkClient
import com.example.newsappkmp.storage.Storage

class NewsService(
    private val networkClient: NetworkClient,
    private val storage: Storage
) {

    fun loadData() {
        // Тут буде реалізація завантаження даних з API
    }
}

