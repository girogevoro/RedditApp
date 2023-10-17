package com.girogevoro.redditapp.data

import com.girogevoro.redditapp.domian.entity.RedditListing
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditListingsDataSource {
    @GET("r/aww/hot.json")
    suspend fun hot(@Query("after") after: String): RedditListing
}