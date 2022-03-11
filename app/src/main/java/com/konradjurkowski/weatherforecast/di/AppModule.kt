package com.konradjurkowski.weatherforecast.di

import com.konradjurkowski.weatherforecast.network.WeatherApi
import com.konradjurkowski.weatherforecast.network.retrofit.RetrofitBuilder
import com.konradjurkowski.weatherforecast.repository.WeatherRepository
import com.konradjurkowski.weatherforecast.screens.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel(get(), get()) }

    factory { RetrofitBuilder() }
    single<WeatherApi> { get<RetrofitBuilder>().buildService() }
    single { WeatherRepository(get()) }
}
