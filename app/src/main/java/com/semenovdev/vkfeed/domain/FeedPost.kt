package com.semenovdev.vkfeed.domain

import android.os.Parcelable
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.google.gson.Gson
import com.semenovdev.vkfeed.R
import com.semenovdev.vkfeed.navigation.Screen
import kotlinx.parcelize.Parcelize


@Parcelize
data class FeedPost(
    val id: Int = -1,
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
) : Parcelable {
    companion object {
       val NavigationType: NavType<FeedPost> = object: NavType<FeedPost>(false) {
           override fun put(
               bundle: SavedState,
               key: String,
               value: FeedPost,
           ) {
               bundle.putParcelable(key, value)
           }

           override fun get(
               bundle: SavedState,
               key: String,
           ): FeedPost? {
               return bundle.getParcelable(key)
           }

           override fun parseValue(value: String): FeedPost {
               return Gson().fromJson<FeedPost>(value, FeedPost::class.java)
           }

       }
    }
}
