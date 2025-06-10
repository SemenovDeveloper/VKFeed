package com.semenovdev.vkfeed.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.semenovdev.vkfeed.navigation.AppNavGraph
import com.semenovdev.vkfeed.navigation.rememberNavigationState
import com.semenovdev.vkfeed.presentation.comments.CommentsScreen
import com.semenovdev.vkfeed.presentation.news.NewsFeedScreen
import com.semenovdev.vkfeed.presentation.main.NavigationItem

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val items =
                    listOf(NavigationItem.Home, NavigationItem.Favorites, NavigationItem.Profile)
                items.forEachIndexed { index, item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(item.titleResId))
                        },
                        selected = selected,
                        onClick = {
                            if(!selected) {
                                navigationState.navigateToTab(item.screen.route)
                            }
                        },
                        colors = NavigationBarItemColors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            selectedIndicatorColor = Color.Unspecified,
                            unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                            unselectedTextColor = MaterialTheme.colorScheme.tertiary,
                            disabledIconColor = MaterialTheme.colorScheme.tertiary,
                            disabledTextColor = MaterialTheme.colorScheme.tertiary,
                        ),
                    )
                }
            }
        },
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                AppNavGraph(
                    navHostController = navigationState.navHostController,
                    newsFeedScreenContent = {
                        NewsFeedScreen(onCommentClickListener = {
                            navigationState.navigateToComments(it)
                        })
                    },
                    favoritesScreenContent = {
                        TextCounter("Favourites")
                    },
                    profileScreenContent = {
                        TextCounter("Profile")
                    },
                    commentsScreenContent = { post ->
                        CommentsScreen(
                            onBackPress = {
                                navigationState.navHostController.popBackStack()
                            },
                            post = post
                        )
                    }
                )

            }

        }
    )
}

@Composable
fun TextCounter(
    name: String,
) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    Text(
        text = "Name:$name, count:$count",
        modifier = Modifier.clickable {
            count += 1
        }
    )
}
