package com.semenovdev.vkfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.semenovdev.vkfeed.ui.AuthState
import com.semenovdev.vkfeed.ui.LoginScreen
import com.semenovdev.vkfeed.ui.MainScreen
import com.semenovdev.vkfeed.ui.MainViewModel
import com.semenovdev.vkfeed.ui.theme.VKFeedTheme
import com.vk.api.sdk.VK
import com.vk.id.VKID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VKID.init(this)
        enableEdgeToEdge()
        setContent {
            VKFeedTheme {
                val viewModel: MainViewModel = viewModel()
                val authState = viewModel.authState.observeAsState(AuthState.Initial)

                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    viewModel.performAuthResult(it)
                }
                when(authState.value) {
                    AuthState.Authorized -> {
                        MainScreen()
                    }
                    AuthState.Unauthorized -> {
                        LoginScreen(
                            onLoginClick = {
                                viewModel.performAuth()
//                                launcher.launch(listOf(VKScope.WALL))
                            }
                        )}
                    AuthState.Initial -> null
                }
            }
        }
    }
}
