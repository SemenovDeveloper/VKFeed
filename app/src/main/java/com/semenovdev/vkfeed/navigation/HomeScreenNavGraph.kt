package com.semenovdev.vkfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.semenovdev.vkfeed.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (post: FeedPost) -> Unit
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
            content = {
                val postId: Int = it.arguments?.getInt(Screen.KEY_POST_ID) ?: 0
                commentsScreenContent(FeedPost(id = postId))
            }
        )
    }
}