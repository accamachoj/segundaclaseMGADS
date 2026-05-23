package me.juancepeda.fotosmarteapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.juancepeda.fotosmarteapp.ui.MarsUiState

@Composable
fun HomeScreen (marsUiState: MarsUiState,  modifier: Modifier = Modifier){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        when (marsUiState) {
            is MarsUiState.Loading -> LoadingScreen()
            is MarsUiState.Success -> ResultScreen(marsUiState.photos.size.toString())
            is MarsUiState.Error -> ErrorScreen()
        }
    }
}

@Composable
fun LoadingScreen(){
    Text("Pantalla de carga...")
}

@Composable
fun ErrorScreen(){
    Text("Pantalla de error.")
}
@Composable
fun ResultScreen(text: String){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = text)
    }
}