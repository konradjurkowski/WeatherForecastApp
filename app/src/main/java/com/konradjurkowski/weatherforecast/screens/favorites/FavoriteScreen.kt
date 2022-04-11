package com.konradjurkowski.weatherforecast.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.konradjurkowski.weatherforecast.R
import com.konradjurkowski.weatherforecast.model.Favorite
import com.konradjurkowski.weatherforecast.navigation.WeatherScreens
import com.konradjurkowski.weatherforecast.widgets.WeatherAppBar
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = getViewModel()
) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Favorites Cities",
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                onButtonClicked = { navController.popBackStack() }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.regular_padding))
        ) {
            val list = viewModel.favList.collectAsState().value
            LazyColumn {
                items(items = list) {
                    CityRow(
                        favorite = it,
                        onDeleteFavorite = { favorite ->
                            viewModel.removeFavorite(favorite)
                        },
                        onItemClicked = { favorite ->
                            navController.navigate("${WeatherScreens.MainScreen.name}/${favorite.city}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CityRow(
    favorite: Favorite,
    onDeleteFavorite: (Favorite) -> Unit,
    onItemClicked: (Favorite) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.small_padding))
            .height(50.dp)
            .clickable { onItemClicked(favorite) },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color(0xFFB2DFDB)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = favorite.city,
                modifier = Modifier.padding(start = 4.dp)
            )
            Surface(
                modifier = Modifier.padding(4.dp),
                shape = CircleShape,
                color = Color(0xFFD1E3E1)
            ) {
                Text(text = favorite.country)
            }
            IconButton(
                onClick = { onDeleteFavorite(favorite) }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "delete",
                    tint = Color.Red.copy(alpha = 0.6f)
                )
            }
        }
    }
}
