package com.example.newsappkmp.push

sealed class PushNotificationResult {

    data class Success(
        val token: String
    ) : PushNotificationResult()

    data class Failure(
        val error: Throwable
    ) : PushNotificationResult()
}

