import com.girogevoro.redditapp.data.RedditListingsDataSource
import com.girogevoro.redditapp.data.room.RedditListingRoom
import com.girogevoro.redditapp.data.room.RedditListingsCacheDao
import com.girogevoro.redditapp.domian.entity.PostData

class RedditListingService(
    private val api: RedditListingsDataSource,
    private val cache: RedditListingsCacheDao,
) {
    private val pagesLastItems = arrayListOf("null")

    suspend fun getHotPosts(
        page: Int,
    ): List<PostData> {
        if (page < 1) {
            throw RuntimeException()
        }
        return cache.getByPage(page)?.posts ?: run {
            val afterAnchor =
                pagesLastItems.getOrNull(page - 1)
                    ?: run {
                        fetchAnchorsForPages(page)
                        pagesLastItems.last()
                    }
            api.hot(afterAnchor).data.children.map { it.data }.also {
                cache.insert(RedditListingRoom(page, it))
            }
        }
    }

    private suspend fun fetchAnchorsForPages(lastPage: Int) {
        for (i in pagesLastItems.size until lastPage) {
            api.hot(pagesLastItems[i - 1]).data.after?.let { pagesLastItems.add(it) }
                ?: break
        }
    }
}
