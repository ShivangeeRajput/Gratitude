package com.example.gratitude.ui.fragments.dashboard.screens.meditation.model

data class YouTubeItem(
    val id: Id?,
    val snippet: Snippet?
)

data class Id(
    val videoId: String?
)


data class Snippet(
    val title: String?,
    val channelTitle: String?,
    val thumbnails: Thumbnails?
)
data class Thumbnails(
    val medium: Thumbnail?
)

data class Thumbnail(
    val url: String?
)