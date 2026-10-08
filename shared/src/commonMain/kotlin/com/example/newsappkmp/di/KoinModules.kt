package com.example.newsappkmp.di

import com.example.newsappkmp.network.NetworkClient
import com.example.newsappkmp.service.NewsService
import com.example.newsappkmp.usecase.NewsUseCase
import com.example.newsappkmp.viewmodel.NewsViewModel
import org.koin.dsl.module

// Лістинг 7.21: Реєстрація сервісів та мережі
val networkModule = module {
    single { NetworkClient() }
    single { NewsService(get()) }
}

// Модуль для UseCase
val useCaseModule = module {
    factory { NewsUseCase(get()) }
}

// Модуль для ViewModel
val viewModelModule = module {
    factory { NewsViewModel(get()) }
}

// Список усіх модулів для легкого запуску
val appKoinModules = listOf(
    networkModule,
    useCaseModule,
    viewModelModule
)