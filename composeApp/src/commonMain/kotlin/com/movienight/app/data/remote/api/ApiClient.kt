package com.movienight.app.data.remote.api

import com.movienight.app.getEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.io.bytestring.ByteString
import kotlinx.io.bytestring.decodeToString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import movienight.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

interface ApiClient {
    suspend fun <T : Any> get(path: String, serializer: KSerializer<T>): T
}

private val jsonBuilder = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

object KtorClient : ApiClient {

    private val httpClient = HttpClient(getEngine()) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(jsonBuilder)
        }
    }

    override suspend fun <T : Any> get(path: String, serializer: KSerializer<T>): T {
        val url = "$BASE_URL$path"
        val response = httpClient.get(url)

        if (!response.status.isSuccess())
            throw Exception("Error: ${response.status} from api:$url")

        val body = response.bodyAsText()

        return jsonBuilder.decodeFromString(serializer, body)
    }


    private const val BASE_URL = "https://www.freetestapi.com/api/v1/"
}

object LocalApiClient : ApiClient {

    @OptIn(ExperimentalResourceApi::class)
    override suspend fun <T : Any> get(path: String, serializer: KSerializer<T>): T {
        val bytes = Res.readBytes(MOVIES_JSON_PATH)
        val jsonContent = ByteString(bytes).decodeToString()

        return jsonBuilder.decodeFromString(serializer, jsonContent)
    }

    private const val MOVIES_JSON_PATH = "files/movies.json"
}