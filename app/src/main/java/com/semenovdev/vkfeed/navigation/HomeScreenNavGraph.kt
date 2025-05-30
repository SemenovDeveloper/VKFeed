package com.semenovdev.vkfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.semenovdev.vkfeed.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (post: FeedPost) -> Unit,
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable(
            route = Screen.NewsFeed.route,
            content = { newsFeedScreenContent() }
        )
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(name = Screen.KEY_FEED_POST) {
                    type = FeedPost.NavigationType
                }
            ),
            content = {
                val post = it.arguments?.getParcelable<FeedPost>(Screen.KEY_FEED_POST)
                    ?: throw RuntimeException("FeedPost is null")
                commentsScreenContent(post)
            }
        )
    }
}