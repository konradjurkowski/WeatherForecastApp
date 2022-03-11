package com.konradjurkowski.weatherforecast.screens.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.konradjurkowski.weatherforecast.R
import com.konradjurkowski.weatherforecast.utils.Constant.API_HOME_URL
import com.konradjurkowski.weatherforecast.widgets.WeatherAppBar

@Composable
fun AboutScreen(
    navController: NavController
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = stringResource(id = R.string.about_item_label),
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                onButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.about_app),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.about_api),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(API_HOME_URL)
                    context.startActivity(intent)
                }
            )
        }
    }
}
