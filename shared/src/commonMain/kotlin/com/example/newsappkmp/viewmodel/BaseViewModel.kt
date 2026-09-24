package com.example.newsappkmp.viewmodel

import com.example.newsappkmp.util.uiDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

open class BaseViewModel(val dispatcher: CoroutineDispatcher = uiDispatcher) {
    val scope: CoroutineScope = CoroutineScope(dispatcher)

    open fun onCleared() {
        scope.cancel()
    }
}