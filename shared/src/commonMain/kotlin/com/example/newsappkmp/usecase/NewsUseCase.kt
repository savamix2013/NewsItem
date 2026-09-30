package com.example.newsappkmp.usecase

import com.example.newsappkmp.data.NewsItemsList
import com.example.newsappkmp.service.NewsService

class NewsUseCase : BaseUseCase<Unit, NewsItemsList?>() {
    // TODO: переробити на DI
    private val newsService: NewsService = NewsService()

    override suspend fun execute(param: Unit): NewsItemsList? {
        return newsService.loadNews().getOrNull()
    }
}