package com.semenovdev.vkfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable (
            route = Screen.NewsFeed.route,
            content = { newsFeedScreenContent() }
        )
        composable(
            route = Screen.Comments.route,
            content = { commentsScreenContent() }
        )
    }
}