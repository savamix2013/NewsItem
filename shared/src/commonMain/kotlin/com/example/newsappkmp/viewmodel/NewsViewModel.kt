package com.example.newsappkmp.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.newsappkmp.data.NewsItemsList
import com.example.newsappkmp.usecase.NewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val useCase: NewsUseCase
) : BaseViewModel() {

    var newsFlow = MutableStateFlow<NewsItemsList?>(null)

    fun loadNews() {
        viewModelScope.launch {
            val result = useCase.invoke(Unit)
            result.getOrNull()?.let {
                newsFlow.tryEmit(it)
            }
        }
    }
}