package com.example.weatherfromthemoonraven.data.api

import com.example.weatherfromthemoonraven.Constant
import retrofit2.Retrofit

object NetworkModule {
    val retrofit: Retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL).build()
}