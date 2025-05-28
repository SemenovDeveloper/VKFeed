package com.semenovdev.vkfeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semenovdev.vkfeed.domain.FeedPost
import com.semenovdev.vkfeed.domain.PostStatistic
import com.semenovdev.vkfeed.ui.HomeScreenState

class MainViewModel: ViewModel() {

    private val initialPosts = mutableListOf<FeedPost>().apply {
        repeat(5) {
            add(FeedPost(
                id = it + 1
            ))
        }
    }

    private val initialState = HomeScreenState.Posts(initialPosts)


    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState>
        get() = _screenState

    fun updateStatic(post: FeedPost, item: PostStatistic) {
        val newStatistic = post.statistics.map {oldItem ->
            if (oldItem.type == item.type) {
                PostStatistic(
                    type = oldItem.type,
                    count = oldItem.count + 1
                )
            } else {
                oldItem
            }
        }
        val currentPosts = screenState.value?.toMutableList() ?: mutableListOf()

        _screenState.value = currentPosts.map {
            if (it == post) {
                it.copy(statistics = newStatistic)
            } else {
                it
            }
        }
    }

    fun deletePost(post: FeedPost) {
        val currentPosts = screenState.value?.toMutableList() ?: mutableListOf()
        currentPosts.remove(post)
        _screenState.value = currentPosts
    }
}