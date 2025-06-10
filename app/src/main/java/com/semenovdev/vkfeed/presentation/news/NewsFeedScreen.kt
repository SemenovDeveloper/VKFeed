package com.semenovdev.vkfeed.presentation.news

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.semenovdev.vkfeed.domain.FeedPost

@Composable
fun NewsFeedScreen(
    onCommentClickListener: (post: FeedPost) -> Unit
) {
    val viewModel: NewsFeedViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)

    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> FeedPosts(
            posts = currentState.posts,
            viewModel = viewModel,
            onCommentClickListener = onCommentClickListener
        )
        NewsFeedScreenState.Initial -> {}
    }
}

@Composable
fun FeedPosts(
    posts: List<FeedPost>,
    viewModel: NewsFeedViewModel,
    onCommentClickListener: (post: FeedPost) -> Unit
) {
    LazyColumn {
        items(
            items = posts,
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
                    onViewClickListener = { statistic ->
                        viewModel.updateStatistic(post, statistic)
                    },
                    onShareClickListener = { statistic ->
                        viewModel.updateStatistic(post, statistic)
                    },
                    onCommentClickListener = {
                        onCommentClickListener(post)
                    },
                    onLikeClickListener = { statistic ->
                        viewModel.updateStatistic(post, statistic)
                    },
                )
            }
        }
    }
}