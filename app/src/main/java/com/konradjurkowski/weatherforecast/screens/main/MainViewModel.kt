package com.konradjurkowski.weatherforecast.screens.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konradjurkowski.weatherforecast.model.Favorite
import com.konradjurkowski.weatherforecast.model.Weather
import com.konradjurkowski.weatherforecast.repository.WeatherDbRepository
import com.konradjurkowski.weatherforecast.repository.WeatherRepository
import com.konradjurkowski.weatherforecast.utils.Result
import com.konradjurkowski.weatherforecast.utils.UiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val city: String,
    private val remoteRepository: WeatherRepository,
    private val repository: WeatherDbRepository
) : ViewModel() {

    private val weather = mutableStateOf<UiResult<Weather>>(UiResult.Loading)

    init {
        initWeather()
    }

    fun getWeather() = weather.value

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) { repository.insertFavorite(favorite) }

    private fun initWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = remoteRepository.getWeather(city)
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
