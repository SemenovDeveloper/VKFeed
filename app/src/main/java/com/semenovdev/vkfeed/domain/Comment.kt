package com.semenovdev.vkfeed.domain

import com.semenovdev.vkfeed.R

data class Comment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatar: Int = R.drawable.post_comunity_thumbnail,
    val text: String = "Long comment text",
    val publicationDate: String = "14:00"
)
