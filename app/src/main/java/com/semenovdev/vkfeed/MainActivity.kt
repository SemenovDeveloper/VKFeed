package com.semenovdev.vkfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.semenovdev.vkfeed.ui.ActivityResultTest
import com.semenovdev.vkfeed.ui.theme.VKFeedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKFeedTheme {
                ActivityResultTest()
            }
        }
    }
}
