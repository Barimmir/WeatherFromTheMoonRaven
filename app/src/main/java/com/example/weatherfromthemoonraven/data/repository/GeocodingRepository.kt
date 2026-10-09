package com.example.weatherfromthemoonraven.data.repository

import com.example.weatherfromthemoonraven.data.api.GeocodingApi
import com.example.weatherfromthemoonraven.data.mapper.toDomain
import com.example.weatherfromthemoonraven.domain.Geocoding

class GeocodingRepository(
    val geocodingApi: GeocodingApi
) {
    suspend fun searchCity(name: String): Result<Geocoding> {
        return try {
            val response = geocodingApi.searchCity(name = name)
            val city = response.toDomain()
            Result.success(city)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}