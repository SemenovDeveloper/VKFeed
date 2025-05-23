package com.semenovdev.vkfeed.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.semenovdev.vkfeed.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val feedPosts = viewModel.feedPosts.observeAsState(listOf())
    LazyColumn() {
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