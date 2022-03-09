package com.konradjurkowski.weatherforecast.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.konradjurkowski.weatherforecast.R
import com.konradjurkowski.weatherforecast.model.Weather
import com.konradjurkowski.weatherforecast.navigation.WeatherScreens
import com.konradjurkowski.weatherforecast.utils.Constant.BASE_PHOTO_URL
import com.konradjurkowski.weatherforecast.utils.UiResult
import com.konradjurkowski.weatherforecast.utils.formatDate
import com.konradjurkowski.weatherforecast.utils.formatDecimals
import com.konradjurkowski.weatherforecast.widgets.HumidityWindyPressureRow
import com.konradjurkowski.weatherforecast.widgets.SunsetSunRiseRow
import com.konradjurkowski.weatherforecast.widgets.WeatherAppBar
import com.konradjurkowski.weatherforecast.widgets.WeatherImage
import com.konradjurkowski.weatherforecast.widgets.WeeklyForecast
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = getViewModel()
) {
    when (val weatherResult = viewModel.getWeather()) {
        is UiResult.Success -> {
            MainScaffold(weather = weatherResult.data, navController = navController)
        }
        is UiResult.Failure -> {
            Box(Modifier.fillMaxSize()) {
                Text(text = "Cannot load weather!")
            }
        }
        UiResult.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun MainScaffold(
    weather: Weather,
    navController: NavController
) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "${weather.city.name}, ${weather.city.country}",
                onAddActionClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                },
                elevation = 6.dp
            )
        }
    ) {
        MainContent(weather = weather)
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    weather: Weather
) {
    val imageUrl = "$BASE_PHOTO_URL${weather.list[0].weather[0].icon}.png"
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.small_padding))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(weather.list[0].dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.regular_padding))
        )
        Surface(
            Modifier
                .padding(dimensionResource(id = R.dimen.small_padding))
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherImage(imageUrl = imageUrl)
                Text(
                    text = "${formatDecimals(weather.list[0].temp.day)}°",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = weather.list[0].weather[0].main,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        HumidityWindyPressureRow(weatherItem = weather.list[0])
        Divider()
        SunsetSunRiseRow(weatherItem = weather.list[0])
        WeeklyForecast(weather = weather)
    }
}
