package com.geeks.appshop.data.di

import com.geeks.appshop.data.datasourse.StoreApi
import com.geeks.appshop.data.repository.ProductRepositoryImpl
import com.geeks.appshop.domain.repository.ProductRepository
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val json = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
    coerceInputValues = true
}

private const val BASE_URL = "https://fakestoreapi.com/"

val dataModule = module {
    single {
        json.asConverterFactory("application/json".toMediaType())
    }

    single<Interceptor>(named("Logging")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder().addInterceptor(get<Interceptor>(named("Logging")))
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(get())
            .client(get())
            .build()
            .create(StoreApi::class.java)
    }

    single<ProductRepository> { ProductRepositoryImpl(get()) }
}