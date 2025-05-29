package com.semenovdev.vkfeed.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.semenovdev.vkfeed.R
import com.semenovdev.vkfeed.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector,
) {

    object Home : NavigationItem(
        screen = Screen.Home,
        titleResId = R.string.navigation_item_home,
        icon = Icons.Outlined.Home
    )

    object Favorites : NavigationItem(
        screen = Screen.Favourites,
        titleResId = R.string.navigation_item_favourites,
        icon = Icons.Outlined.FavoriteBorder
    )

    object Profile : NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}
