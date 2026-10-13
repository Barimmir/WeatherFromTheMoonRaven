package com.example.weatherfromthemoonraven.presentation.state

import com.example.weatherfromthemoonraven.domain.City
import com.example.weatherfromthemoonraven.domain.Weather

data class WeatherState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSearching: Boolean = false,
    val searchError: String? = null,
    val selectedCity: City? = null
)