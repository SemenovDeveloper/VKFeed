package com.semenovdev.vkfeed.navigation

import com.semenovdev.vkfeed.domain.FeedPost

sealed class Screen (
    val route: String
) {
    object Home: Screen(ROUTE_HOME)
    object NewsFeed: Screen(ROUTE_NEWS_FEED)
    object Comments: Screen(ROUTE_COMMENTS) {
        private const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(post: FeedPost): String {
            return "$ROUTE_FOR_ARGS/${post.id}"
        }
    }
    object Favourites: Screen(ROUTE_FAVOURITES)
    object Profile: Screen(ROUTE_PROFILE)


    companion object {
        const val KEY_POST_ID = "post_id"


        const val ROUTE_HOME = "home"
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_COMMENTS = "comments/${KEY_POST_ID}"
        const val ROUTE_FAVOURITES = "favourites"
        const val ROUTE_PROFILE = "profile"


    }
}