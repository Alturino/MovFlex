package com.onirutla.movflex.core.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onirutla.movflex.core.util.Constants.TMDB_STARTING_PAGE_INDEX
import okio.IOException
import retrofit2.HttpException

class PagingDataSource<R : Any>(
    private inline val apiService: suspend (position: Int) -> List<R>
) : PagingSource<Int, R>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, R> = try {
        val position = params.key ?: TMDB_STARTING_PAGE_INDEX
        val response = apiService(position)
        val nextKey = if (response.isEmpty()) null else position + 1
        LoadResult.Page(
            data = response,
            prevKey = if (position == TMDB_STARTING_PAGE_INDEX) null else position - 1,
            nextKey = nextKey
        )
    } catch (exception: IOException) {
        LoadResult.Error(exception)
    } catch (exception: HttpException) {
        LoadResult.Error(exception)
    }

    override fun getRefreshKey(state: PagingState<Int, R>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}