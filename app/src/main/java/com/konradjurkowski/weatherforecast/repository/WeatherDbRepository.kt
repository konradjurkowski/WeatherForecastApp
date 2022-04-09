package com.konradjurkowski.weatherforecast.repository

import com.konradjurkowski.weatherforecast.data.WeatherDao
import com.konradjurkowski.weatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow

class WeatherDbRepository(
    private val weatherDao: WeatherDao
) {
    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)
    suspend fun deleteFavorites() = weatherDao.deleteAllFavorites()
}
