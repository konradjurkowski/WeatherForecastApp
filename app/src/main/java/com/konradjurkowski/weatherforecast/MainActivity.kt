package com.konradjurkowski.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.konradjurkowski.weatherforecast.navigation.WeatherNavigation
import com.konradjurkowski.weatherforecast.ui.theme.WeatherForecastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecastTheme {
                WeatherNavigation()
            }
        }
    }
}
