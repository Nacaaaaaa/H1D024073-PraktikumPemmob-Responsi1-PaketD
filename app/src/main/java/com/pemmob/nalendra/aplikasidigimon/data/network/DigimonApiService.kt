package com.pemmob.nalendra.aplikasidigimon.data.network

import com.pemmob.nalendra.aplikasidigimon.data.model.DigimonDetailResponse
import com.pemmob.nalendra.aplikasidigimon.data.model.DigimonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Retrofit API Interface untuk mendefinisikan endpoint
interface DigimonApiService {

    // Endpoint untuk mendapatkan daftar Digimon
    @GET("api/v1/digimon")
    suspend fun getDigimonList(
        @Query("pageSize") pageSize: Int = 20,
        @Query("page") page: Int = 0
    ): DigimonListResponse

    // Endpoint untuk mendapatkan detail spesifik Digimon berdasarkan ID
    @GET("api/v1/digimon/{id}")
    suspend fun getDigimonDetail(
        @Path("id") id: Int
    ): DigimonDetailResponse
}