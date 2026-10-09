package com.example.weatherfromthemoonraven

object ConstantNetwork {
    const val BASE_WEATHER_URL =
        "https://api.open-meteo.com/"
    const val CURRENT_REQUEST =
        "temperature_2m,apparent_temperature,is_day,wind_speed_10m,weather_code,relative_humidity_2m"
    const val BASE_GEOCODING_URL =
        "https://geocoding-api.open-meteo.com/v1/search?name=Berlin&count=10&language=en&format=json"
}