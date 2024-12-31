package com.movienight.app.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import io.ktor.http.isSuccess
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend inline fun <reified T> get(path: String): T {
        val url = URLBuilder()
            .takeFrom(BASE_URL)
            .apply {
                encodedPath = path
            }.build()

        val response = httpClient.get(url)

        if (!response.status.isSuccess())
            throw Exception("Error: ${response.status} from api")

        return response.body()
    }

    const val BASE_URL = "https://www.freetestapi.com/api/v1/"

}