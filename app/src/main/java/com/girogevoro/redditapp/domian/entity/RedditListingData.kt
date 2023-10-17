package com.girogevoro.redditapp.domian.entity

data class RedditListingData(
    val after: String?,
    val before: String?,
    val dist: Int,
    val children: List<RedditListingDataItem>
)