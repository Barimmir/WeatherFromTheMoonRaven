package com.example.weatherfromthemoonraven.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val current: CurrentDTO?,
)

@Serializable
data class CurrentDTO(
    val time: String,
    @SerialName("temperature_2m")
    val temperature: Double,
    @SerialName("apparent_temperature")
    val apparentTemperature: Double,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("weather_code")
    val weatherCode: Int,
    @SerialName("relative_humidity_2m")
    val relativeHumidity: Int,
    @SerialName("wind_speed_10m")
    val windSpeed: Double,
)

