package com.semenovdev.vkfeed.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun PostCard() {
    Text( text = "Post")
}

@Preview(showBackground = true)
@Composable
fun PreviewPostCard() {
    PostCard()
}