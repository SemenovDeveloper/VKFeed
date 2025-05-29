package com.semenovdev.vkfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun AppNavGraph (
    navHostController: NavHostController,
    newsFeedScreenContent: @Composable () -> Unit,
    favoritesScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        homeScreenNavGraph(
            newsFeedScreenContent = newsFeedScreenContent,
            commentsScreenContent = commentsScreenContent
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