package com.example.weatherfromthemoonraven.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherfromthemoonraven.data.api.NetworkModule
import com.example.weatherfromthemoonraven.data.repository.GeocodingRepository
import com.example.weatherfromthemoonraven.data.repository.WeatherRepository
import com.example.weatherfromthemoonraven.presentation.action.WeatherAction
import com.example.weatherfromthemoonraven.presentation.model.WeatherViewModel

@Composable
fun MainScreen() {
    val viewModel = remember {
        WeatherViewModel(
            WeatherRepository(NetworkModule.weatherApi), GeocodingRepository(
                NetworkModule.geocodingApi
            )
        )
    }
    val city = "Berlin"
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onAction(WeatherAction.SearchCity(city))
    }
}