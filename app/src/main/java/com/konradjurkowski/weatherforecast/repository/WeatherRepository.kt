package com.konradjurkowski.weatherforecast.repository

import com.konradjurkowski.weatherforecast.model.Weather
import com.konradjurkowski.weatherforecast.network.WeatherApi
import com.konradjurkowski.weatherforecast.utils.Result
import java.lang.Exception

class WeatherRepository(
    private val api: WeatherApi
) {

    suspend fun getWeather(cityQuery: String): Result<Weather> {
        try {
            return api.getWeather(cityQuery).run {
                takeIf { it.isSuccessful }?.body()?.let { Result.Success(it) }
                    ?: return Result.Failure(Throwable("AN ERROR OCCURRED: $this"))
            }
        } catch (error: Exception) {
            return Result.Failure(Throwable("AN ERROR OCCURRED: $error"))
        }
    }
}
