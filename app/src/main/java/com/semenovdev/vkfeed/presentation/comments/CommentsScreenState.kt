package com.semenovdev.vkfeed.presentation.comments

import com.semenovdev.vkfeed.domain.Comment
import com.semenovdev.vkfeed.domain.FeedPost

sealed class CommentsScreenState {
    object Initial: CommentsScreenState()

    data class Comments(
        val post: FeedPost,
        val comments: List<Comment>,
    ) : CommentsScreenState()
}