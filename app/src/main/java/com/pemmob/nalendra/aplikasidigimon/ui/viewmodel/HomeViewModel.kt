package com.pemmob.nalendra.aplikasidigimon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.nalendra.aplikasidigimon.data.model.DigimonListItem
import com.pemmob.nalendra.aplikasidigimon.data.repository.DigimonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// State UI Class (Data, Loading, Error)
sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val digimons: List<DigimonListItem>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

// ViewModel (MVVM) untuk Home Screen
class HomeViewModel(
    private val repository: DigimonRepository = DigimonRepository()
) : ViewModel() {

    // Menyimpan state dari UI (Encapsulation: backing property)
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchDigimons()
    }

    fun fetchDigimons() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            val result = repository.getDigimonList(page = 0)
            
            result.onSuccess { response ->
                val list = response.content ?: emptyList()
                _uiState.value = HomeUiState.Success(list)
            }.onFailure { exception ->
                _uiState.value = HomeUiState.Error(exception.message ?: "Terjadi kesalahan tak terduga")
            }
        }
    }
}