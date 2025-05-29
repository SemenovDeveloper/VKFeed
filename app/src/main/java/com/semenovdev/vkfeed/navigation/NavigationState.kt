package com.semenovdev.vkfeed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.semenovdev.vkfeed.domain.FeedPost

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateToTab(route: String)  {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToComments(post: FeedPost) {
        navHostController.navigate(Screen.Comments.getRouteWithArgs(post))
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}