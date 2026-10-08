package com.example.newsappkmp.usecase

import com.example.newsappkmp.data.NewsItemsList
import com.example.newsappkmp.service.NewsService

// Перенесення залежності сервісу в конструктор UseCase
class NewsUseCase(
    private val newsService: NewsService
) : BaseUseCase<Unit, NewsItemsList?>() {

    override suspend fun execute(param: Unit): NewsItemsList? {
        val result = newsService.loadNews()
        return result.getOrNull()
    }
}