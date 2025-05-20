package com.semenovdev.vkfeed.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.semenovdev.vkfeed.R

sealed class NavigationItem(
    val titleResId: Int,
    val icon: ImageVector,
) {

    object Home: NavigationItem(
        titleResId = R.string.navigation_item_home,
        icon = Icons.Outlined.Home
    )

    object Favorites : NavigationItem(
        titleResId = R.string.navigation_item_favourites,
        icon = Icons.Outlined.FavoriteBorder
    )

    object Profile: NavigationItem(
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}