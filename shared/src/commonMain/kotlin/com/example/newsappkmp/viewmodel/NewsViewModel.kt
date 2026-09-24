package com.example.newsappkmp.viewmodel

import com.example.newsappkmp.data.NewsItem
import com.example.newsappkmp.service.NewsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsService: NewsService = NewsService(
        httpClient = TODO()
    )
) : BaseViewModel() {

    private val _news = MutableStateFlow<List<NewsItem>>(emptyList())
    val news: StateFlow<List<NewsItem>> = _news.asStateFlow()

    fun loadNews() {
        // Завдяки BaseViewModel ми маємо доступ до поля scope
        scope.launch {
            // Коли сервіс отримає дані, записуємо їх у стейт
            val items = newsService.getNews()
            _news.value = items
        }
    }
}