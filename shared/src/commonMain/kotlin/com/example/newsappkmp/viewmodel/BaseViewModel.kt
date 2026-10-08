package com.example.newsappkmp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

open class BaseViewModel : ViewModel() {
    val scope: CoroutineScope = this.viewModelScope
}