package com.konradjurkowski.weatherforecast.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konradjurkowski.weatherforecast.model.Favorite
import com.konradjurkowski.weatherforecast.repository.WeatherDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: WeatherDbRepository
) : ViewModel() {

    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged().collect { listOfFavs ->
                _favList.value = listOfFavs
            }
        }
    }

    fun removeFavorite(favorite: Favorite) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(favorite)
            repository.getFavorites().distinctUntilChanged().collect { listOfFavs ->
                _favList.value = listOfFavs
            }
        }
}
