package com.pemmob.nalendra.aplikasidigimon.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton Object (Fitur Kotlin) untuk menyediakan instance Retrofit
object ApiClient {
    private const val BASE_URL = "https://digi-api.com/"

    // Lazy initialization memastikan Retrofit hanya dibuat saat dibutuhkan
    val apiService: DigimonApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DigimonApiService::class.java)
    }
}