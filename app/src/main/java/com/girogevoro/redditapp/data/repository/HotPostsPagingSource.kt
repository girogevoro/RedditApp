package com.girogevoro.redditapp.data.repository

import RedditListingService
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.girogevoro.redditapp.data.repository.RedditListingRepositoryImpl.Companion.NETWORK_PAGE_SIZE
import com.girogevoro.redditapp.domian.entity.PostData
import retrofit2.HttpException
import java.io.IOException

class HotPostsPagingSource(
    private val service: RedditListingService,
) : PagingSource<Int, PostData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostData> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getHotPosts(page = pageIndex)
            val prevKey = if (pageIndex > 1) pageIndex - 1 else null
            val nextKey = if (response.isNotEmpty()) pageIndex + 1 else null
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey,
                itemsBefore = (pageIndex - 1) * NETWORK_PAGE_SIZE
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
