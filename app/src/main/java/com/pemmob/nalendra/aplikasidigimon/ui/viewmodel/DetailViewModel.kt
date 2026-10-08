package com.pemmob.nalendra.aplikasidigimon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.nalendra.aplikasidigimon.data.model.DigimonDetailResponse
import com.pemmob.nalendra.aplikasidigimon.data.repository.DigimonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// State UI Class (Data, Loading, Error)
sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val digimon: DigimonDetailResponse) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

// ViewModel (MVVM) untuk Digimon Detail Screen
class DetailViewModel(
    private val repository: DigimonRepository = DigimonRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun fetchDigimonDetail(id: Int) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            val result = repository.getDigimonDetail(id)

            result.onSuccess { response ->
                _uiState.value = DetailUiState.Success(response)
            }.onFailure { exception ->
                _uiState.value = DetailUiState.Error(exception.message ?: "Terjadi kesalahan tak terduga")
            }
        }
    }
}