package com.girogevoro.redditapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RedditListingRoom::class], version = 1)
@TypeConverters(Converters::class)
abstract class RedditListingsCacheDatabase : RoomDatabase() {
    abstract fun dao(): RedditListingsCacheDao
}