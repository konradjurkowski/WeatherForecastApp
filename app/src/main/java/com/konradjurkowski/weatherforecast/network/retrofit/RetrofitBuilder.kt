package com.konradjurkowski.weatherforecast.network.retrofit

import com.konradjurkowski.weatherforecast.network.WeatherApi
import com.konradjurkowski.weatherforecast.utils.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun buildService(): WeatherApi = buildRetrofit().create(WeatherApi::class.java)

}
