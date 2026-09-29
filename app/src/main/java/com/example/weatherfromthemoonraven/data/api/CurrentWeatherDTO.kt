package com.example.weatherfromthemoonraven.data.api

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDTO(
    val forecast: String?,
    val latitude: String,
    val longitude: String,
    val currentTemperature: String,
    )