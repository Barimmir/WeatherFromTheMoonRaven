package com.example.weatherfromthemoonraven.presentation.state

import com.example.weatherfromthemoonraven.domain.City
import com.example.weatherfromthemoonraven.domain.Weather

data class WeatherState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val cities: List<City> = emptyList(),
    val isSearching: Boolean = false,
    val searchError: String? = "",
    val isSearchMode: Boolean = false,
)