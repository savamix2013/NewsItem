package com.example.newsappkmp.viewmodel

import com.example.newsappkmp.data.NewsItemsList
import com.example.newsappkmp.usecase.NewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : BaseViewModel() {
    // TODO: переробити на DI
    private val useCase: NewsUseCase = NewsUseCase()

    private val _newsFlow = MutableStateFlow<NewsItemsList?>(null)
    val newsFlow: StateFlow<NewsItemsList?> = _newsFlow.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadNews() {
        scope.launch {
            val result = useCase.invoke(Unit)
            result.onSuccess { data ->
                _newsFlow.value = data
            }.onFailure { exception ->
                _errorMessage.value = exception.message ?: "Невідома помилка мережі"
            }
        }
    }
}
