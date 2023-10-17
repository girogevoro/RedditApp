package com.girogevoro.redditapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.girogevoro.redditapp.domian.entity.RedditListing

@Dao
interface RedditListingsCacheDao {
    @Insert
    suspend fun insert(vararg users: RedditListingRoom)

    @Query("SELECT * FROM RedditListingRoom WHERE page LIKE :cashedPage LIMIT 1")
    suspend fun getByPage(cashedPage: Int): RedditListingRoom?
}