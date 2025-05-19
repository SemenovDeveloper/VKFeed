package com.semenovdev.vkfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.semenovdev.vkfeed.ui.theme.VKFeedTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKFeedTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {


                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "TopAppBar")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            val items = listOf("Songs", "Artists", "Playlists")
            val selectedIcons = listOf<ImageVector>(
                Icons.Filled.Search,
                Icons.Filled.Face,
                Icons.Filled.Favorite,

            )

            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector =  selectedIcons[index],
                                contentDescription = null
                            )
                        },
                        label = { Text(item) },
                        selected = true,
                        onClick = {}
                    )
                }
            }
        },
    ) {
        val drawerState = rememberDrawerState(DrawerValue.Open)
        val scope = rememberCoroutineScope()
        // icons to mimic drawer destinations
        val items =
            listOf(
                Icons.Default.Favorite,
                Icons.Default.Favorite,
                Icons.Default.Favorite,
                Icons.Default.Favorite,
                Icons.Default.Favorite,
                )
        ModalNavigationDrawer(
            modifier = Modifier.padding(it),
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(drawerState) {
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        items.forEach { item ->
                            Spacer(Modifier.height(8.dp))
                            NavigationDrawerItem(
                                icon = { Icon(item, contentDescription = null) },
                                label = { Text(item.name.substringAfterLast(".")) },
                                selected = true,
                                onClick = {

                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { scope.launch { drawerState.open() } }) { Text("Click to open") }
                }
            }
        )
        Text(
            text = "",
            modifier = Modifier.padding(it)
        )
    }
}
