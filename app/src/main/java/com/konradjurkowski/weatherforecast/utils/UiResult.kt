package com.konradjurkowski.weatherforecast.utils

sealed class UiResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : UiResult<T>()
    data class Failure(val error: Throwable) : UiResult<Nothing>()
    object Loading : UiResult<Nothing>()
}
