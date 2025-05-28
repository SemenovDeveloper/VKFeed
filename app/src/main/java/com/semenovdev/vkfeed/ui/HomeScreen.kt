package com.semenovdev.vkfeed.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.semenovdev.vkfeed.MainViewModel
import com.semenovdev.vkfeed.domain.FeedPost

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)



    when (val currentState = screenState.value) {
        is HomeScreenState.Comments -> CommentsScreen(
            feedPost = currentState.post,
            comments = currentState.comments,
        )
        is HomeScreenState.Posts -> FeedPosts(
            posts = currentState.posts,
            viewModel = viewModel
        )

        HomeScreenState.Initial -> {}
    }

}

@Composable
fun FeedPosts(
    posts: List<FeedPost>,
    viewModel: MainViewModel
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