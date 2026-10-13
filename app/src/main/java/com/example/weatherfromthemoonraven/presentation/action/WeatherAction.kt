package com.example.weatherfromthemoonraven.presentation.action

sealed interface WeatherAction {
    data class LoadWeather(
        val latitude: Double,
        val longitude: Double
    ) : WeatherAction

    data object Refresh : WeatherAction
    data class SearchCity(val query: String) : WeatherAction
}
