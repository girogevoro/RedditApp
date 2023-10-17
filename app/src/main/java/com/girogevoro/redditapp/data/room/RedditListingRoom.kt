package com.girogevoro.redditapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.girogevoro.redditapp.domian.entity.PostData

@Entity
data class RedditListingRoom(
    @PrimaryKey val page: Int,
    @ColumnInfo(name = "posts") val posts: List<PostData>
)