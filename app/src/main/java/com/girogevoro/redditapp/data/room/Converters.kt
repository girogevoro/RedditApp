package com.girogevoro.redditapp.data.room

import androidx.room.TypeConverter
import com.girogevoro.redditapp.domian.entity.PostData
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<PostData>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<PostData>::class.java).toList()
}