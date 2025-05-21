package com.semenovdev.vkfeed.domain

data class PostStatistic(
    val type: StatisticType,
    val count: Int = 0
)

enum class StatisticType {
    VIEWS, COMMENTS, SHARES, LIKES
}
