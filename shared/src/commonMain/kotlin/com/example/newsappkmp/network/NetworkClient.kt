package com.example.newsappkmp.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

enum class Method {
    GET,
    POST,
    PUT,
    DELETE
}

expect fun createHttpClient(): HttpClient

class NetworkClient(
    private val networkConfiguration: NetworkConfiguration = NetworkConfiguration(),
    val httpClient: HttpClient = createHttpClient()
) {
    suspend inline fun <reified T> request(
        path: String,
        method: Method = Method.GET,
        body: Any? = null,
        headers: Map<String, String> = emptyMap()
    ): Result<T> {
        val fullUrl = if (path.startsWith("http")) path else "${NetworkConfiguration.BASE_URL}$path"

        return try {
            val response = when (method) {
                Method.GET -> httpClient.get(fullUrl) {
                    headers.forEach { (key, value) -> header(key, value) }
                }
                Method.POST -> httpClient.post(fullUrl) {
                    contentType(ContentType.Application.Json)
                    body?.let { setBody(it) }
                    headers.forEach { (key, value) -> header(key, value) }
                }
                Method.PUT -> httpClient.put(fullUrl) {
                    contentType(ContentType.Application.Json)
                    body?.let { setBody(it) }
                    headers.forEach { (key, value) -> header(key, value) }
                }
                Method.DELETE -> httpClient.delete(fullUrl) {
                    headers.forEach { (key, value) -> header(key, value) }
                }
            }

            val result = response.body<T>()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}