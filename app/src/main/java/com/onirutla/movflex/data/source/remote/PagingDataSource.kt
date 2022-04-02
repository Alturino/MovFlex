package com.onirutla.movflex.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onirutla.movflex.data.source.remote.response.detail.PageResponse
import com.onirutla.movflex.util.Constants.TMDB_STARTING_PAGE_INDEX
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

class PagingDataSource<R : Any>(
    private inline val apiService: suspend (position: Int) -> Response<PageResponse<R>>
) : PagingSource<Int, R>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, R> = try {
        val position = params.key ?: TMDB_STARTING_PAGE_INDEX
        val response = apiService(position)
        val movies = response.body()!!.results
        val nextKey = if (movies.isEmpty()) null else position + 1
        LoadResult.Page(
            data = movies,
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