package com.semenovdev.vkfeed.presentation.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semenovdev.vkfeed.domain.FeedPost

class CommentsViewModelFactory(
    private val post: FeedPost
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return CommentsViewModel(post = post) as T
    }
}