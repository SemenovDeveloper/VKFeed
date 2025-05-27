package com.semenovdev.vkfeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semenovdev.vkfeed.domain.FeedPost
import com.semenovdev.vkfeed.domain.PostStatistic
import com.semenovdev.vkfeed.ui.NavigationItem

class MainViewModel: ViewModel() {

    private val initFeedPosts = mutableListOf<FeedPost>().apply {
        repeat(5) {
            add(FeedPost(
                id = it + 1
            ))
        }
    }


    private val _feedPosts = MutableLiveData<List<FeedPost>>(initFeedPosts)
    val feedPosts: LiveData<List<FeedPost>>
        get() = _feedPosts

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
        val currentPosts = feedPosts.value?.toMutableList() ?: mutableListOf()

        _feedPosts.value = currentPosts.map {
            if (it == post) {
                it.copy(statistics = newStatistic)
            } else {
                it
            }
        }
    }

    fun deletePost(post: FeedPost) {
        val currentPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
        currentPosts.remove(post)
        _feedPosts.value = currentPosts
    }
}