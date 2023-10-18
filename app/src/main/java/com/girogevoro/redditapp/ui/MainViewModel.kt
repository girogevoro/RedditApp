package com.girogevoro.redditapp.ui

import RedditListingService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.girogevoro.redditapp.data.RedditListingsDataSource
import com.girogevoro.redditapp.data.repository.RedditListingRepository
import com.girogevoro.redditapp.data.repository.RedditListingRepositoryImpl
import com.girogevoro.redditapp.data.room.RedditListingsCacheDao
import com.girogevoro.redditapp.domian.entity.PostData
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val api: RedditListingsDataSource,
    private val cache: RedditListingsCacheDao
) : ViewModel() {
    private val postsRepository: RedditListingRepository =
        RedditListingRepositoryImpl(RedditListingService(api, cache))

    fun getHotPosts(): Flow<PagingData<PostData>> {
        return postsRepository.getHot().cachedIn(viewModelScope)
    }
}