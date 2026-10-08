package com.example.newsappkmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.newsappkmp.di.KoinDIFactory
import com.example.newsappkmp.di.resolve
import com.example.newsappkmp.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Лістинг 7.48: Отримання ViewModel через наш спільний DI
        val vm: NewsViewModel = KoinDIFactory.resolve(NewsViewModel::class)!!

        setContent {
            NewsListScreen(vm)
        }
    }
}