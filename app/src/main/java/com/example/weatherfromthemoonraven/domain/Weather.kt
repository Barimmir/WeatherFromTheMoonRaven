package com.example.weatherfromthemoonraven.domain

data class Weather(
    val current: CurrentWeather?,
)

data class CurrentWeather(
    val time: String,
    val temperature: Double,
    val apparentTemperature: Double,
    val isDay: Boolean,
    val weatherType: WeatherType,
    val relativeHumidity: Double,
    val windSpeed: Double,
)