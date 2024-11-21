package com.wavelinemedia.shopper_fk.data.di

import android.util.Log
import com.wavelinemedia.shopper_fk.data.network.NetworkServiceImpl
import com.wavelinemedia.shopper_fk.domain.network.NetworkService
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("SHOPPER_BACKEND", message)
                    }
                }
            }
        }
        client
    }
    single<NetworkService> {
        NetworkServiceImpl(get())
    }
}