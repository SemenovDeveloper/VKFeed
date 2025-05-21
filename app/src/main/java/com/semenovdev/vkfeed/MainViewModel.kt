package com.semenovdev.vkfeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semenovdev.vkfeed.domain.FeedPost
import com.semenovdev.vkfeed.domain.PostStatistic

class MainViewModel: ViewModel() {
    private val _feedPost = MutableLiveData(FeedPost())
    val feedPost: LiveData<FeedPost>
        get() = _feedPost

    fun updateStatic(item: PostStatistic) {
        val newStatistic = feedPost.value?.statistics?.map {oldItem ->
            if (oldItem.type == item.type) {
                PostStatistic(
                    type = oldItem.type,
                    count = oldItem.count + 1
                )
            } else {
                oldItem
            }
        } ?: throw IllegalStateException("FeedPost cant bee null")

        _feedPost.value = feedPost.value?.copy(statistics = newStatistic)
    }
}