package com.semenovdev.vkfeed

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.semenovdev.vkfeed.ui.MainScreen
import com.semenovdev.vkfeed.ui.theme.VKFeedTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKFeedTheme {
                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    when(it) {
                        is VKAuthenticationResult.Success -> {
                            Log.d("MainActivity test", "Success")
                        }
                        is VKAuthenticationResult.Failed -> {
                            Log.d("MainActivity test", "Failed")
                        }
                    }
                }
                launcher.launch(listOf(VKScope.WALL))
                MainScreen()
            }
        }
    }
}
