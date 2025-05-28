package com.semenovdev.vkfeed.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semenovdev.vkfeed.domain.Comment
import com.semenovdev.vkfeed.domain.FeedPost

class CommentsViewModel: ViewModel() {
    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(FeedPost(1))
    }

    fun loadComments(post: FeedPost) {
        val comments = mutableListOf<Comment>().apply {
            repeat(20) {
                add(Comment(id = it))
            }
        }

        _screenState.value = CommentsScreenState.Comments(
            post = post,
            comments = comments
        )
    }
}