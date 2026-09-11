package com.example.newsappkmp.push

interface PushNotificationManager {

    suspend fun registerForPushNotifications(): PushNotificationResult

    suspend fun unregisterFromPushNotifications()

    suspend fun getToken(): PushNotificationResult

    fun setNotificationHandler(
        handler: (NotificationPayload) -> Unit
    )
}

