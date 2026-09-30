package com.example.newsappkmp.util

import kotlinx.serialization.json.Json

object JsonDecoder {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    inline fun <reified T> decode(jsonString: String): T {
        return json.decodeFromString(jsonString)
    }
}