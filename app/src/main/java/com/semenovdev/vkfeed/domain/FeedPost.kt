package com.semenovdev.vkfeed.domain

import com.semenovdev.vkfeed.R

data class FeedPost(
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "Кричу: единственным разработчиком AnduinOS, дистрибутива Linux с интерфейсом как у Windows 11, оказался сотрудник Microsoft Андуин Сюэ",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<PostStatistic> = listOf(
        PostStatistic(
            type = StatisticType.VIEWS,
            count = 687,
        ),
        PostStatistic(
            type = StatisticType.SHARES,
            count = 100,
        ),
        PostStatistic(
            type = StatisticType.COMMENTS,
            count = 11,
        ),
        PostStatistic(
            type = StatisticType.LIKES,
            count = 22,
        ),
    )
)
