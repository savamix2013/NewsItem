package com.example.newsappkmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.newsappkmp.shared.BuildConfig
import com.example.newsappkmp.viewmodel.NewsViewModel
import com.example.newsappkmp.network.NetworkConfiguration

class MainActivity : ComponentActivity() {

    private val viewModel: NewsViewModel by lazy {
        NewsViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Передаємо API-ключ зі BuildConfig
        // у спільний NetworkConfiguration
        NetworkConfiguration.apiKey = BuildConfig.NEWS_API_KEY

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsListScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
