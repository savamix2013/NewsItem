package com.example.newsappkmp.usecase

import com.example.newsappkmp.data.NewsItemsList
import com.example.newsappkmp.service.NewsService

class NewsUseCase(
    private val newsService: NewsService
) : BaseUseCase<Unit, NewsItemsList?>() {

    override suspend fun execute(param: Unit): NewsItemsList? {
        return newsService.loadNews().getOrNull()
    }
}