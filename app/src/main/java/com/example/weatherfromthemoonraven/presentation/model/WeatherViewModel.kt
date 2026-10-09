package com.example.weatherfromthemoonraven.presentation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherfromthemoonraven.data.repository.GeocodingRepository
import com.example.weatherfromthemoonraven.data.repository.WeatherRepository
import com.example.weatherfromthemoonraven.domain.City
import com.example.weatherfromthemoonraven.presentation.action.WeatherAction
import com.example.weatherfromthemoonraven.presentation.state.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val geocodingRepository: GeocodingRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    fun onAction(action: WeatherAction) {
        when (action) {
            is WeatherAction.LoadWeather -> loadWeather(action.latitude, action.longitude)
            is WeatherAction.SearchCity -> searchCity(action.query)
            is WeatherAction.Refresh -> refresh()

        }
    }

    private fun loadWeather(
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val result =
                weatherRepository.getCurrentWeather(latitude = latitude, longitude = longitude)
            result.onSuccess { weather ->
                _state.update { it.copy(weather = weather, isLoading = false) }
            }.onFailure { exception ->
                _state.update { it.copy(error = exception.message, isLoading = false) }
            }
        }
    }

    private fun searchCity(name: String) {
        viewModelScope.launch {
            _state.update { it.copy(isSearching = true, searchError = null) }
            val result = geocodingRepository.searchCity(name = name)
            result.onSuccess { geocoding ->
                val city = geocoding.results?.firstOrNull()
                if (city != null) {
                    _state.update {
                        it.copy(
                            selectedCity = city,
                            isSearching = false,
                        )
                    }
                    loadWeather(latitude = city.latitude, longitude = city.longitude)
                } else {
                    _state.update {
                        it.copy(
                            isSearching = false,
                            searchError = "Город не найден",
                        )
                    }
                }
            }.onFailure { exception ->
                _state.update { it.copy(searchError = exception.message, isSearching = false) }
            }
        }
    }

    private fun refresh() {
        val city = _state.value.selectedCity ?: return
        loadWeather(latitude = city.latitude, longitude = city.longitude)
    }
}