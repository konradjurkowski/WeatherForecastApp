package com.konradjurkowski.weatherforecast

import android.app.Application
import com.konradjurkowski.weatherforecast.di.appModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@WeatherApp)
            modules(appModule)
        }
    }
}
