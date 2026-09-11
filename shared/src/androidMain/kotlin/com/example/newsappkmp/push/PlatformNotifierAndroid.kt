package com.example.newsappkmp.push

import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

class PlatformNotifierAndroid : PlatformNotifier {

    private val firebaseMessaging = FirebaseMessaging.getInstance()

    override suspend fun register(): String {
        return firebaseMessaging.token.await()
    }

    override fun unregister() {
        firebaseMessaging.deleteToken()
    }

    override suspend fun getToken(): String {
        return firebaseMessaging.token.await()
    }
}

