package com.example.weatherfromthemoonraven.domain

data class Geocoding(
    val results: List<City>?
)

data class City(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String
)