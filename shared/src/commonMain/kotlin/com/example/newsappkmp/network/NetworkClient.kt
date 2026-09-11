package com.example.newsappkmp.network

import com.example.newsappkmp.data.NewsItemsList

class NetworkClient(
    private val networkConfiguration: NetworkConfiguration
) {

    fun loadData(): NewsItemsList? {
        // Тут буде реалізація запиту до API
        return null
    }
}

