package com.example.weatherfromthemoonraven.data.repository

import com.example.weatherfromthemoonraven.ConstantNetwork
import com.example.weatherfromthemoonraven.data.api.WeatherApi
import com.example.weatherfromthemoonraven.data.mapper.toDomain
import com.example.weatherfromthemoonraven.domain.Weather

class WeatherRepository(
    val api: WeatherApi,
) {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
    ): Result<Weather> {
        return try {
            val response = api.getCurrentWeather(
                latitude = latitude,
                longitude = longitude,
                current = ConstantNetwork.CURRENT_REQUEST
            )
            val weather = response.toDomain()
            Result.success(weather)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}