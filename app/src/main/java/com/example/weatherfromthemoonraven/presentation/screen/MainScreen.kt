package com.example.weatherfromthemoonraven.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherfromthemoonraven.presentation.action.WeatherAction
import com.example.weatherfromthemoonraven.presentation.model.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Погода") })
    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    modifier = modifier.weight(1f),
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text(text = "Введите город") })
                Button(
                    modifier = modifier.weight(0.5f),
                    onClick = { viewModel.onAction(WeatherAction.SearchCity(query)) }) {
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
            }
        }
    }
}