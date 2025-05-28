package com.semenovdev.vkfeed

import androidx.compose.runtime.currentRecomposeScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semenovdev.vkfeed.domain.Comment
import com.semenovdev.vkfeed.domain.FeedPost
import com.semenovdev.vkfeed.domain.PostStatistic
import com.semenovdev.vkfeed.ui.HomeScreenState

class MainViewModel : ViewModel() {

    private val comments = mutableListOf<Comment>().apply {
        repeat(20) {
            add(
                Comment(id = it)
            )
        }
    }

    private val initialPosts = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(
                FeedPost(
                    id = it + 1
                )
            )
        }
    }

    private val initialState = HomeScreenState.Posts(initialPosts)


    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState>
        get() = _screenState

    fun updateStatistic(post: FeedPost, item: PostStatistic) {
        val currentState = screenState.value

        if (currentState !is HomeScreenState.Posts) return

        val newStatistic = post.statistics.map { oldItem ->
            if (oldItem.type == item.type) {
                PostStatistic(
                    type = oldItem.type,
                    count = oldItem.count + 1
                )
            } else {
                oldItem
            }
        }

        val currentPosts = currentState.posts.toMutableList()

        val updatedPosts = currentPosts.map {
            if (it == post) {
                it.copy(statistics = newStatistic)
            } else {
                it
            }
        }
        _screenState.value = HomeScreenState.Posts(posts = updatedPosts)


    }

    fun deletePost(post: FeedPost) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return

        val currentPosts = currentState.posts.toMutableList()
        currentPosts.remove(post)
        _screenState.value = HomeScreenState.Posts(currentPosts)
    }

    private var savedState: HomeScreenState.Posts = HomeScreenState.Posts(listOf())

    fun showComments(post: FeedPost) {
        val currentState = screenState.value

        if (currentState is HomeScreenState.Posts) {
            savedState = currentState
        }

        _screenState.value = HomeScreenState.Comments(
            post = post,
            comments = comments
        )

    }

    fun closeComments() {
        _screenState.value = savedState
    }
}