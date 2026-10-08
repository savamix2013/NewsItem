package com.example.newsappkmp

import android.app.Application
import com.example.newsappkmp.di.KoinDIFactory

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Ініціалізуємо спільний Koin DI
        KoinDIFactory.di
    }
}