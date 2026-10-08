package com.pemmob.nalendra.aplikasidigimon.data.repository

import com.pemmob.nalendra.aplikasidigimon.data.model.DigimonDetailResponse
import com.pemmob.nalendra.aplikasidigimon.data.model.DigimonListResponse
import com.pemmob.nalendra.aplikasidigimon.data.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Repository Class untuk menangani pengambilan data dari Network/API.
// Membantu memisahkan logika UI dengan sumber data (sesuai MVVM)
class DigimonRepository {
    private val apiService = ApiClient.apiService

    suspend fun getDigimonList(page: Int = 0): Result<DigimonListResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDigimonList(pageSize = 20, page = page)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getDigimonDetail(id: Int): Result<DigimonDetailResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDigimonDetail(id)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}