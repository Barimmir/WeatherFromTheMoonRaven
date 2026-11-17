package com.example.weatherfromthemoonraven.di

import com.example.weatherfromthemoonraven.ConstantNetwork
import com.example.weatherfromthemoonraven.data.api.GeocodingApi
import com.example.weatherfromthemoonraven.data.api.WeatherApi
import com.example.weatherfromthemoonraven.data.repository.GeocodingRepository
import com.example.weatherfromthemoonraven.data.repository.WeatherRepository
import com.example.weatherfromthemoonraven.presentation.model.WeatherViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val appModule = module {
    single { Json { ignoreUnknownKeys = true } }
    single {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level =
                HttpLoggingInterceptor.Level.BODY
        }).build()
    }
    single(named("weather")) {
        Retrofit.Builder().baseUrl(ConstantNetwork.BASE_WEATHER_URL).client(get<OkHttpClient>())
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }
    single(named("geocoding")) {
        Retrofit.Builder().baseUrl(ConstantNetwork.BASE_GEOCODING_URL).client(get<OkHttpClient>())
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }
    single { get<Retrofit>(named("weather")).create(WeatherApi::class.java) }
    single { get<Retrofit>(named("geocoding")).create(GeocodingApi::class.java) }

    single { WeatherRepository(get<WeatherApi>()) }
    single { GeocodingRepository(get<GeocodingApi>()) }

    viewModelOf(::WeatherViewModel)
}