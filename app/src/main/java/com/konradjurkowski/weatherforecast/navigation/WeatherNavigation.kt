package com.konradjurkowski.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.konradjurkowski.weatherforecast.screens.about.AboutScreen
import com.konradjurkowski.weatherforecast.screens.favorites.FavoriteScreen
import com.konradjurkowski.weatherforecast.screens.main.MainScreen
import com.konradjurkowski.weatherforecast.screens.search.SearchScreen
import com.konradjurkowski.weatherforecast.screens.settings.SettingsScreen
import com.konradjurkowski.weatherforecast.screens.splash.WeatherSplashScreen
import com.konradjurkowski.weatherforecast.utils.Constant.DEFAULT_CITY
import com.konradjurkowski.weatherforecast.utils.NavigationArguments.CITY

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }
        composable(
            route = "${WeatherScreens.MainScreen.name}/{$CITY}",
            arguments = listOf(
                navArgument(name = CITY) {
                    type = NavType.StringType
                }
            )
        ) {
            val city = it.arguments?.getString(CITY) ?: DEFAULT_CITY
            MainScreen(
                navController = navController,
                city = city
            )
        }
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name) {
            FavoriteScreen(navController = navController)
        }
        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }
    }
}
