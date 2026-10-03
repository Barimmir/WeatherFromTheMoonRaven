package com.example.weatherfromthemoonraven.presentation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherfromthemoonraven.data.repository.WeatherRepository
import com.example.weatherfromthemoonraven.presentation.action.WeatherAction
import com.example.weatherfromthemoonraven.presentation.state.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    fun onAction(action: WeatherAction) {
        when (action) {
            is WeatherAction.LoadWeather -> getLoadWeather()
            is WeatherAction.Refresh -> refresh()
        }
    }

    private fun getLoadWeather() {
        viewModelScope.launch {

        }

    }

    private fun refresh() {}

}