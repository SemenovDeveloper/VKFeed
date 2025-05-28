package com.semenovdev.vkfeed.ui

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.semenovdev.vkfeed.domain.FeedPost
import com.semenovdev.vkfeed.navigation.AppNavGraph
import com.semenovdev.vkfeed.navigation.rememberNavigationState

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(NavigationItem.Home, NavigationItem.Favorites, NavigationItem.Profile)
                items.forEachIndexed { index, item ->
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
                        selected = item.screen.route == currentRoute,
                        onClick = {
                            navigationState.navigateTo(item.screen.route)
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
            Box (modifier = Modifier.fillMaxSize().padding(it)){
                AppNavGraph(
                    navHostController = navigationState.navHostController,
                    homeScreenContent = {
                        if (commentsToPost.value == null) {
                            HomeScreen(onCommentClickListener = {
                                commentsToPost.value = it
                            })
                        } else {
                            CommentsScreen(
                                onBackPress = {
                                    commentsToPost.value = null
                                },
                                post = commentsToPost.value!!
                            )
                        }

                    },
                    favoritesScreenContent = {
                        TextCounter("Favourites")
                    },
                    profileScreenContent = {
                        TextCounter("Profile")
                    }
                )

            }

        }
    )
}

@Composable
fun TextCounter(
    name: String
) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
  Text(
      text = "Name:$name, count:$count",
      modifier = Modifier.clickable{
          count += 1
      }
  )
}
