package com.example.newsappkmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.newsappkmp.di.appDI
import com.example.newsappkmp.viewmodel.NewsViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class MainActivity : ComponentActivity(), DIAware {

    // Підключаємо спільний контейнер DI з commonMain
    override val di: DI = appDI

    // Лістинг 7.13: отримання NewsViewModel через lazy-делегат Kodein
    private val viewModel: NewsViewModel by di.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    NewsListScreen(viewModel = viewModel)
                }
            }
        }
    }
}