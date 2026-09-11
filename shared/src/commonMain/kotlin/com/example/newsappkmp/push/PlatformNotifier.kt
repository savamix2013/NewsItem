package com.example.newsappkmp.push

interface PlatformNotifier {

    suspend fun register(): String

    fun unregister()

    suspend fun getToken(): String
}
