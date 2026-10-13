package com.example.weatherfromthemoonraven.data.mapper

import com.example.weatherfromthemoonraven.data.api.CityDTO
import com.example.weatherfromthemoonraven.data.api.CurrentDTO
import com.example.weatherfromthemoonraven.data.api.GeocodingResponse
import com.example.weatherfromthemoonraven.data.api.WeatherResponse
import com.example.weatherfromthemoonraven.domain.City
import com.example.weatherfromthemoonraven.domain.CurrentWeather
import com.example.weatherfromthemoonraven.domain.Geocoding
import com.example.weatherfromthemoonraven.domain.Weather
import com.example.weatherfromthemoonraven.domain.WeatherType

fun WeatherResponse.toDomain(): Weather {
    return Weather(current = current?.toDomain())
}

fun CurrentDTO.toDomain(): CurrentWeather {
    return CurrentWeather(
        time = time,
        temperature = temperature,
        apparentTemperature = apparentTemperature,
        isDay = isDay.isDayConverter(),
        weatherType = weatherCode.weatherTypeConverter(),
        relativeHumidity = relativeHumidity,
        windSpeed = windSpeed
    )
}

private fun Int.isDayConverter(): Boolean {
    return this != 0
}

private fun Int.weatherTypeConverter(): WeatherType {
    return when (this) {
        0 -> WeatherType.CLEAR
        1, 2, 3 -> WeatherType.CLOUDY
        45, 48 -> WeatherType.FOGGY
        51, 53, 55, 56, 57 -> WeatherType.DRIZZLE
        61, 63, 65, 66, 67 -> WeatherType.RAIN
        71, 73, 75, 77 -> WeatherType.SNOW
        80, 81, 82 -> WeatherType.SHOWER
        85, 86 -> WeatherType.SNOW
        95, 96, 99 -> WeatherType.THUNDERSTORM
        else -> WeatherType.UNKNOWN
    }
}

fun GeocodingResponse.toDomain(): Geocoding {
    return Geocoding(results = results?.toDomain())
}

fun CityDTO.toDomain(): City {
    return City(
        id = id, name = name,
        latitude = latitude,
        longitude = longitude,
        country = country
    )
}

private fun List<CityDTO>.toDomain(): List<City> {
    return this.map { it.toDomain() }
}