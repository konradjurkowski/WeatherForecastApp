package com.konradjurkowski.weatherforecast.network

import com.konradjurkowski.weatherforecast.model.Weather
import com.konradjurkowski.weatherforecast.utils.Constant.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = API_KEY,
    ): Response<Weather>
}
