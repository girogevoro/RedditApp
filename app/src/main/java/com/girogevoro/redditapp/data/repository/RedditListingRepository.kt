package com.girogevoro.redditapp.data.repository

import androidx.paging.PagingData
import com.girogevoro.redditapp.domian.entity.PostData
import kotlinx.coroutines.flow.Flow

interface RedditListingRepository {
    fun getHot(): Flow<PagingData<PostData>>
}
