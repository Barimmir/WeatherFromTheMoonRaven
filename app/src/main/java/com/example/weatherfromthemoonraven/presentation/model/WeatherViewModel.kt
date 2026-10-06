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
            is WeatherAction.LoadWeather -> loadWeather()
            is WeatherAction.Refresh -> refresh()
        }
    }

    private fun loadWeather() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            val result = weatherRepository.getCurrentWeather(52.52, 13.41)
            result.onSuccess { weather ->
                _state.value = _state.value.copy(weather = weather, isLoading = false)
            }.onFailure { exception ->
                _state.value = _state.value.copy(error = exception.message, isLoading = false)
            }
        }
    }
    private fun refresh() {}

}