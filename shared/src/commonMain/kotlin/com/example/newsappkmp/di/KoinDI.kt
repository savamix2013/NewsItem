package com.example.newsappkmp.di

import com.example.newsappkmp.network.NetworkClient
import com.example.newsappkmp.service.NewsService
import com.example.newsappkmp.usecase.NewsUseCase
import com.example.newsappkmp.viewmodel.NewsViewModel
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module
import kotlin.reflect.KClass

class KoinDI : KoinComponent {

    val serviceModule = module {
        single { NetworkClient() }
        single { NewsService(get()) }
    }

    val useCaseModule = module {
        factory { NewsUseCase(get()) }
    }

    val vmModule = module {
        factory { NewsViewModel(get()) }
    }

    fun start(): KoinApplication = startKoin {
        modules(
            listOf(
                serviceModule,
                useCaseModule,
                vmModule
            )
        )
    }
}

object KoinDIFactory {
    val di by lazy {
        KoinDI().apply {
            start()
        }
    }
}

fun <T : Any> KoinDIFactory.resolve(clazz: KClass<*>): T? {
    return di.getKoin().get(clazz)
}