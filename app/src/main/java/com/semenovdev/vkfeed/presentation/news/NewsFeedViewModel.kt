package com.semenovdev.vkfeed.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semenovdev.vkfeed.domain.FeedPost
import com.semenovdev.vkfeed.domain.PostStatistic

class NewsFeedViewModel : ViewModel() {
    private val initialPosts = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(
                FeedPost(
                    id = it + 1
                )
            )
        }
    }

    private val initialState = NewsFeedScreenState.Posts(initialPosts)


    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState>
        get() = _screenState

    fun updateStatistic(post: FeedPost, item: PostStatistic) {
        val currentState = screenState.value

        if (currentState !is NewsFeedScreenState.Posts) return

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
        _screenState.value = NewsFeedScreenState.Posts(posts = updatedPosts)
    }

    fun deletePost(post: FeedPost) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val currentPosts = currentState.posts.toMutableList()
        currentPosts.remove(post)
        _screenState.value = NewsFeedScreenState.Posts(currentPosts)
    }
}