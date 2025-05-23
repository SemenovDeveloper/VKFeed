package com.semenovdev.vkfeed.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.semenovdev.vkfeed.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val feedPosts = viewModel.feedPosts.observeAsState(listOf())


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
            LazyColumn(
                modifier = Modifier.padding(it),
            ) {
                items(
                    items = feedPosts.value,
                    key = {
                        it.id
                    }
                ) {post ->
                    val dismissState = rememberSwipeToDismissBoxState(
                        confirmValueChange = {value ->
                            if(value == SwipeToDismissBoxValue.EndToStart) {
                                viewModel.deletePost(post)
                                true
                            } else {
                                false
                            }
                        }
                    )
                    SwipeToDismissBox(
                        modifier = Modifier.animateItem(),
                        state = dismissState,
                        backgroundContent = {}
                    ) {
                        PostCard(
                            feedPost = post,
                            onViewClickListener = {statistic ->
                                viewModel.updateStatic(post, statistic)
                            },
                            onShareClickListener = {statistic ->
                                viewModel.updateStatic(post, statistic)
                            },
                            onCommentClickListener = {statistic ->
                                viewModel.updateStatic(post, statistic)
                            },
                            onLikeClickListener = {statistic ->
                                viewModel.updateStatic(post, statistic)
                            },
                        )
                    }
                }
            }

        }
    )
}
