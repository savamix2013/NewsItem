package com.example.newsappkmp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsappkmp.data.NewsItem
import com.example.newsappkmp.data.NewsItemsList
import com.example.newsappkmp.viewmodel.NewsViewModel

@Composable
fun NewsListScreen(
    viewModel: NewsViewModel,
    onItemClick: (NewsItem) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        viewModel.loadNews()
    }

    val newsList: NewsItemsList? by viewModel.newsFlow.collectAsState()
    val error: String? by viewModel.errorMessage.collectAsState()

    val articles = newsList?.articles.orEmpty()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            articles.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(articles) { item ->
                        NewsRowItem(
                            item = item,
                            onClick = { onItemClick(item) }
                        )
                    }
                }
            }
            error != null -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Помилка: $error",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Завантаження новин...",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun NewsRowItem(
    item: NewsItem,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            text = item.title ?: "Без назви",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
