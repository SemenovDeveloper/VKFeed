package com.semenovdev.vkfeed.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.semenovdev.vkfeed.domain.FeedPost
import com.semenovdev.vkfeed.domain.PostStatistic

@Composable
fun MainScreen() {
    val feedPost = remember {
        mutableStateOf(FeedPost())
    }


    Scaffold(
        bottomBar = {
            val selectedItemIndexState = remember {
                mutableIntStateOf(0)
            }
            val items = listOf(NavigationItem.Home, NavigationItem.Favorites, NavigationItem.Profile)

            NavigationBar {
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
                        selected = selectedItemIndexState.intValue == index,
                        onClick = {
                            selectedItemIndexState.intValue = index
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
            PostCard(
                modifier = Modifier.padding(it),
                feedPost = feedPost.value,
                onStatisticItemClickListener = { item ->
                    val newStatistic = feedPost.value.statistics.map {oldItem ->
                        if (oldItem.type == item.type) {
                            PostStatistic(
                                type = oldItem.type,
                                count = oldItem.count + 1
                            )
                        } else {
                            oldItem
                        }
                    }

                    feedPost.value = feedPost.value.copy(statistics = newStatistic)
                }
            )
        }
    )
}


@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}

@Preview
@Composable
fun PreviewMainScreenDark() {
    MainScreen()
}

