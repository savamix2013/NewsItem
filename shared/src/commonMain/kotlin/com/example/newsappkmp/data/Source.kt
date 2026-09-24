package com.example.newsappkmp.data

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String? = null,
    val name: String? = null
)