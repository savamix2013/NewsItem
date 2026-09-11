package com.example.newsappkmp

import android.app.Application
import com.google.firebase.FirebaseApp
import com.example.newsappkmp.push.NotificationManagerImpl
import com.example.newsappkmp.push.PlatformNotifierAndroid

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        val notificationManager = NotificationManagerImpl(
            PlatformNotifierAndroid()
        )

        notificationManager.setNotificationHandler { payload ->
            // Обробка отриманого сповіщення
        }
    }
}