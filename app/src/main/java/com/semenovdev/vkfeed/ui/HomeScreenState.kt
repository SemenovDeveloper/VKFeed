package com.semenovdev.vkfeed.ui

import com.semenovdev.vkfeed.domain.Comment
import com.semenovdev.vkfeed.domain.FeedPost

sealed class HomeScreenState {

    object Initial: HomeScreenState()

    data class Posts(val posts: List<FeedPost>): HomeScreenState()

    data class Comments(val post: FeedPost, val comments: List<Comment>): HomeScreenState()
}