package com.example.newsappkmp.push

data class NotificationPayload(
    val title: String,
    val body: String,
    val data: Map<String, String>
)

