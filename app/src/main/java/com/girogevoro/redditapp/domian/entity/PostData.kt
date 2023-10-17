package com.girogevoro.redditapp.domian.entity

import com.google.gson.annotations.SerializedName

data class PostData(
    val title: String,
    @SerializedName("ups")
    val upVoted: Int,
    @SerializedName("num_comments")
    val commentsCount: Int,
    @SerializedName("url_overridden_by_dest")
    val postUrl: String,
)