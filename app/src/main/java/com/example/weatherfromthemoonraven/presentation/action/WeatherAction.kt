package com.example.weatherfromthemoonraven.presentation.action

sealed interface WeatherAction{
   data object LoadWeather : WeatherAction
   data object Refresh : WeatherAction
}