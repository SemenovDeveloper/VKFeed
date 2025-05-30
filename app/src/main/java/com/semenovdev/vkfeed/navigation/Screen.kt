package com.semenovdev.vkfeed.navigation

import android.net.Uri
import com.google.gson.Gson
import com.semenovdev.vkfeed.domain.FeedPost

sealed class Screen (
    val route: String
) {
    object Home: Screen(ROUTE_HOME)
    object NewsFeed: Screen(ROUTE_NEWS_FEED)
    object Comments: Screen(ROUTE_COMMENTS) {
        private const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(post: FeedPost): String {
            val postJson = Gson().toJson(post)
            return "$ROUTE_FOR_ARGS/${postJson.encode()}"
        }
    }
    object Favourites: Screen(ROUTE_FAVOURITES)
    object Profile: Screen(ROUTE_PROFILE)


    companion object {
        const val KEY_FEED_POST = "feed_post"


        const val ROUTE_HOME = "home"
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_FAVOURITES = "favourites"
        const val ROUTE_PROFILE = "profile"


    }
}

fun String.encode(): String {
    return Uri.encode(this)
}
