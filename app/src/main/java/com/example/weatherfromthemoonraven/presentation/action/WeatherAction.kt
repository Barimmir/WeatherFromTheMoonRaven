package com.example.weatherfromthemoonraven.presentation.action

import com.example.weatherfromthemoonraven.domain.City

sealed interface WeatherAction {
    data object LoadWeather : WeatherAction
    data object Refresh : WeatherAction
    data class SearchCity(val query: String) : WeatherAction
    data class SelectCity(val city: City) : WeatherAction
}
