package com.example.weatherfromthemoonraven.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherfromthemoonraven.data.api.NetworkModule
import com.example.weatherfromthemoonraven.data.repository.GeocodingRepository
import com.example.weatherfromthemoonraven.data.repository.WeatherRepository
import com.example.weatherfromthemoonraven.presentation.action.WeatherAction
import com.example.weatherfromthemoonraven.presentation.model.WeatherViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = remember {
        WeatherViewModel(
            WeatherRepository(NetworkModule.weatherApi), GeocodingRepository(
                NetworkModule.geocodingApi
            )
        )
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxSize()) {
        Row {
            TextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text(text = "Введите город") })
            Button(onClick = { viewModel.onAction(WeatherAction.SearchCity(query)) }) {
                Text(text = "Найти")
            }
        }
        when {
            state.isLoading || state.isSearching -> CircularProgressIndicator()
            state.searchError != null -> Text(text = state.searchError!!)
            state.error != null -> Text(text = state.error!!)
            state.weather != null -> Text(
                text = state.weather?.current?.temperature?.toString() ?: "Погода не дошла"
            )
            else -> Text(text = "Введите город")
        }
    }
}