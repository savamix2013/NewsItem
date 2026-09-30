package com.example.newsappkmp.usecase

import com.example.newsappkmp.data.NewsItemsList
import com.example.newsappkmp.service.NewsService

class GetNewsUseCase(
    private val newsService: NewsService = NewsService()
) : BaseUseCase<Unit, NewsItemsList>() {

    override suspend fun execute(param: Unit): NewsItemsList {
        val result = newsService.loadNews()
        return result.getOrThrow()
    }
}