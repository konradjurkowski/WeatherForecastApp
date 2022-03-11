package com.konradjurkowski.weatherforecast.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherAppBar(
    title: String,
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    onAddActionClicked: () -> Unit = { },
    onButtonClicked: () -> Unit = { }
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = onAddActionClicked) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search_icon"
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "menu_icon"
                    )
                }
            } else {
                Box { /* no op */ }
            }
        },
        navigationIcon = {
            icon?.let {
                IconButton(
                    onClick = onButtonClicked
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "search_icon",
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}
