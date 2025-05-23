package com.semenovdev.vkfeed.navigation

sealed class Screen (
    val route: String
) {
    object NewsFeed: Screen(ROUTE_NEWS_FEED)
    object Favourites: Screen(ROUTE_FAVOURITES)
    object Profile: Screen(ROUTE_PROFILE)

    private companion object {
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVOURITES = "favourites"
        const val ROUTE_PROFILE = "profile"
    }
}