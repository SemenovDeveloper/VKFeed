package com.semenovdev.vkfeed.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val snackbarHostState = SnackbarHostState()
    Log.d("MainScreen", "Recomposition")
    var scope = rememberCoroutineScope()
    val isSnackBarVisible = remember {
        mutableStateOf(true)
    }

    Scaffold(
        bottomBar = {
            val selectedItemIndexState = remember {
                mutableIntStateOf(0)
            }
            val items = listOf(NavigationItem.Home, NavigationItem.Favorites, NavigationItem.Profile)

            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(item.titleResId))
                        },
                        selected = selectedItemIndexState.intValue == index,
                        onClick = {
                            selectedItemIndexState.intValue = index
                        },
                        colors = NavigationBarItemColors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            selectedIndicatorColor = Color.Unspecified,
                            unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                            unselectedTextColor = MaterialTheme.colorScheme.tertiary,
                            disabledIconColor = MaterialTheme.colorScheme.tertiary,
                            disabledTextColor = MaterialTheme.colorScheme.tertiary,
                        ),
                    )
                }
            }
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                PostCard()
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            )
        },
        floatingActionButton = {
            if (isSnackBarVisible.value) {
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.background,
                    onClick = {
                        scope.launch {
                            val action = snackbarHostState.showSnackbar(
                                message = "Hello",
                                actionLabel = "Hide FAB",
                                duration = SnackbarDuration.Long
                            )
                            if (action == SnackbarResult.ActionPerformed) {
                                isSnackBarVisible.value = false
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null
                    )
                }
            }
        }
    )
}


@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}

@Preview
@Composable
fun PreviewMainScreenDark() {
    MainScreen()
}

