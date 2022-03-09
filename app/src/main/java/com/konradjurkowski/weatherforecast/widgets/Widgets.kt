package com.konradjurkowski.weatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.konradjurkowski.weatherforecast.R
import com.konradjurkowski.weatherforecast.model.Weather
import com.konradjurkowski.weatherforecast.model.WeatherItem
import com.konradjurkowski.weatherforecast.utils.Constant
import com.konradjurkowski.weatherforecast.utils.formatDate
import com.konradjurkowski.weatherforecast.utils.formatDateTime
import com.konradjurkowski.weatherforecast.utils.formatDecimals

@Composable
fun HumidityWindyPressureRow(
    modifier: Modifier = Modifier,
    weatherItem: WeatherItem
) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding))) {
            Icon(
                painter = rememberImagePainter(data = R.drawable.humidity),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
            Text(
                text = "${weatherItem.humidity}%",
                style = MaterialTheme.typography.caption
            )
        }
        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding))) {
            Icon(
                painter = rememberImagePainter(data = R.drawable.pressure),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
            Text(
                text = "${weatherItem.pressure} psi",
                style = MaterialTheme.typography.caption
            )
        }
        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding))) {
            Icon(
                painter = rememberImagePainter(data = R.drawable.wind),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
            Text(
                text = "${weatherItem.speed} m/s",
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun SunsetSunRiseRow(
    modifier: Modifier = Modifier,
    weatherItem: WeatherItem
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.large_padding),
                bottom = dimensionResource(id = R.dimen.regular_padding)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Image(
                painter = rememberImagePainter(data = R.drawable.sunrise),
                contentDescription = "sunrise",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = formatDateTime(weatherItem.sunrise),
                style = MaterialTheme.typography.caption
            )
        }
        Row {
            Text(
                text = formatDateTime(weatherItem.sunset),
                style = MaterialTheme.typography.caption
            )
            Image(
                painter = rememberImagePainter(data = R.drawable.sunset),
                contentDescription = "sunset",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun WeatherDetailRow(
    modifier: Modifier = Modifier,
    weatherItem: WeatherItem
) {
    val imageUrl = "${Constant.BASE_PHOTO_URL}${weatherItem.weather[0].icon}.png"
    Surface(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.small_padding))
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.regular_padding)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formatDate(weatherItem.dt).split(",")[0])
            WeatherImage(imageUrl = imageUrl)
            Surface(
                modifier = Modifier,
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = weatherItem.weather[0].description,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.regular_padding)),
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold
                )
                ) {
                    append(formatDecimals(weatherItem.temp.max) + "°")
                }
                withStyle(style = SpanStyle(
                    color = Color.LightGray,
                )
                ) {
                    append(formatDecimals(weatherItem.temp.min) + "°")
                }
            })
        }
    }
}

@Composable
fun WeeklyForecast(
    modifier: Modifier = Modifier,
    weather: Weather
) {
    Text(
        text = stringResource(id = R.string.this_week_title),
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.Bold
    )
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFFEEF1EF),
        shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.large_padding))
    ) {
        LazyColumn(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.extra_small_padding)),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.extra_small_padding))
        ) {
            items(items = weather.list) { item ->
                WeatherDetailRow(weatherItem = item)
            }
        }
    }
}

@Composable
fun WeatherImage(
    imageUrl: String
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
            }
        ),
        contentDescription = "icon image",
        modifier = Modifier.size(80.dp)
    )
}
