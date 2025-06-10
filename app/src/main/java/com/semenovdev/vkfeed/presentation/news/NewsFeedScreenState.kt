package com.semenovdev.vkfeed.presentation.news

import com.semenovdev.vkfeed.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial: NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>): NewsFeedScreenState()
}