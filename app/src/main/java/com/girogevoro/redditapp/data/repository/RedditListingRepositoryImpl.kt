package com.girogevoro.redditapp.data.repository

import RedditListingService
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.girogevoro.redditapp.domian.entity.PostData
import kotlinx.coroutines.flow.Flow

internal class RedditListingRepositoryImpl(
    private val postsService: RedditListingService,
) : RedditListingRepository {

    override fun getHot(): Flow<PagingData<PostData>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                HotPostsPagingSource(service = postsService)
            }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 27
    }
}