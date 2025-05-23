package com.semenovdev.vkfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph (
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    favoritesScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.NewsFeed.route
    ) {
        composable(
            route = Screen.NewsFeed.route,
            content = {
                homeScreenContent()
            }
        )

        composable(
            route = Screen.Favourites.route,
            content = {
                favoritesScreenContent()
            }
        )

        composable(
            route = Screen.Profile.route,
            content = {
                profileScreenContent()
            }
        )
    }
}