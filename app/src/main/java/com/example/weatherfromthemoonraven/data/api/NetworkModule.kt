package com.example.weatherfromthemoonraven.data.api

import com.example.weatherfromthemoonraven.ConstantNetwork
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {
    private val json: Json = Json { ignoreUnknownKeys = true }
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    private val weatherRetrofit: Retrofit =
        Retrofit.Builder().baseUrl(ConstantNetwork.BASE_WEATHER_URL).client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    val weatherApi: WeatherApi = weatherRetrofit.create(WeatherApi::class.java)
    private val geocodingRetrofit: Retrofit =
        Retrofit.Builder().baseUrl(ConstantNetwork.BASE_GEOCODING_URL).client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    val geocodingApi: GeocodingApi = geocodingRetrofit.create(GeocodingApi::class.java)
}