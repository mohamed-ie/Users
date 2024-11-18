package com.users.core.network.di

import com.users.core.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
internal fun ktorClient(
    engine: HttpClientEngine = OkHttp.create()
) = HttpClient(engine) {
    defaultRequest {
        url(BuildConfig.BASE_URL)
        contentType(ContentType.Application.Json)
    }

    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}