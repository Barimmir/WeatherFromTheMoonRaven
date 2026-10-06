package com.example.weatherfromthemoonraven.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherfromthemoonraven.data.api.NetworkModule
import com.example.weatherfromthemoonraven.data.repository.WeatherRepository
import com.example.weatherfromthemoonraven.presentation.action.WeatherAction
import com.example.weatherfromthemoonraven.presentation.model.WeatherViewModel

@Composable
fun MainScreen() {
    val viewModel = remember {
        WeatherViewModel(WeatherRepository(NetworkModule.weatherApi))
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onAction(WeatherAction.LoadWeather)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text(state.error!!)
                state.weather != null -> Text(
                    state.weather!!.current?.temperature?.toString() ?: "—"
                )

                else -> Text("Нет данных")
            }
        }
    }
}