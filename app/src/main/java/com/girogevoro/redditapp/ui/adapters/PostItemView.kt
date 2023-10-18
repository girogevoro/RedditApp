package com.girogevoro.redditapp.ui.adapters

interface PostItemView {
    val pos: Int
    fun setTitle(title: String)
    fun setRating(rating: String)
    fun setCommentCount(commentCount: String)
}