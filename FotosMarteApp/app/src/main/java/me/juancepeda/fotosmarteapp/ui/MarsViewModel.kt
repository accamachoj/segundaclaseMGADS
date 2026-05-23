package me.juancepeda.fotosmarteapp.ui

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.juancepeda.fotosmarteapp.network.MarPhoto
import me.juancepeda.fotosmarteapp.network.MarsApi

class MarsViewModel : ViewModel(){

    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }
     fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                marsUiState = MarsUiState.Success(MarsApi.retrofitService.getPhotos())
            } catch (e: Exception) {
                Log.println(Log.ERROR, null, e.toString())
                marsUiState = MarsUiState.Error
            }
        }
    }
}

sealed interface MarsUiState {
    data class Success(val photos: List<MarPhoto>): MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}