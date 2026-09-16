package com.example.newsappkmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsappkmp.data.NewsItem
import com.example.newsappkmp.viewmodel.NewsViewModel


@Composable
fun NewsListItemView(
    item: NewsItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        // Сіра заглушка замість картинки
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.LightGray)
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        // Текстова частина
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = item.title.orEmpty(),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = item.content.orEmpty(),
                style = MaterialTheme.typography.titleSmall,
                maxLines = 3
            )

            Text(
                text = item.publishedAt.orEmpty(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Composable
fun NewsListView(
    items: List<NewsItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {

        items(items) { item ->
            NewsListItemView(
                item = item
            )
        }
    }
}


@Composable
fun NewsListScreen(
    viewModel: NewsViewModel
) {

    // Завантажуємо новини
    LaunchedEffect(Unit) {
        viewModel.loadNews()
    }

    // Поки що передаємо порожній список
    NewsListView(
        items = emptyList()
    )
}


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsListScreen(
                viewModel = NewsViewModel()
            )
        }
    }
}
