package com.konradjurkowski.weatherforecast.screens.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konradjurkowski.weatherforecast.model.Weather
import com.konradjurkowski.weatherforecast.repository.WeatherRepository
import com.konradjurkowski.weatherforecast.utils.Result
import com.konradjurkowski.weatherforecast.utils.UiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val city: String,
    private val repository: WeatherRepository
) : ViewModel() {

    private val weather = mutableStateOf<UiResult<Weather>>(UiResult.Loading)

    init {
        initWeather()
    }

    fun getWeather() = weather.value

    private fun initWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getWeather(city)
            when (result) {
                is Result.Success -> {
                    weather.value = UiResult.Success(result.data)
                }
                is Result.Failure -> {
                    weather.value = UiResult.Failure(result.error)
                }
            }
        }
    }
}
