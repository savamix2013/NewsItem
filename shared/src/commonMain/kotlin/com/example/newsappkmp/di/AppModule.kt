package com.example.newsappkmp.di

import com.example.newsappkmp.network.NetworkClient
import com.example.newsappkmp.service.NewsService
import com.example.newsappkmp.usecase.NewsUseCase
import com.example.newsappkmp.viewmodel.NewsViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

// Модуль для мережі та сервісів
val serviceModule = DI.Module("service") {
    bindSingleton { NetworkClient() }
    bindSingleton { NewsService(instance()) }
}

// Модуль для бізнес-логіки (UseCases)
val useCasesModule = DI.Module("useCases") {
    bindProvider { NewsUseCase(instance()) }
}

// Модуль для представлення (ViewModels)
val viewModelsModule = DI.Module("viewModels") {
    bindProvider { NewsViewModel(instance()) }
}

// Головний DI-контейнер, що об'єднує всі модулі
val appDI = DI {
    import(serviceModule)
    import(useCasesModule)
    import(viewModelsModule)
}