package com.example.weatherfromthemoonraven.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingResponse(
    val results: List<CityDTO>?
)

@Serializable
data class CityDTO(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String
)