package com.example.weatherfromthemoonraven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.weatherfromthemoonraven.presentation.screen.MainScreen
import com.example.weatherfromthemoonraven.ui.theme.WeatherFromTheMoonRavenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherFromTheMoonRavenTheme {
                MainScreen()
            }
        }
    }
}