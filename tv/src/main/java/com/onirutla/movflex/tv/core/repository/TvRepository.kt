package com.onirutla.movflex.tv.core.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.onirutla.movflex.core.data.source.remote.PagingDataSource
import com.onirutla.movflex.core.data.source.remote.response.toSeasons
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.domain.model.Season
import com.onirutla.movflex.core.util.Constants.PAGE_SIZE
import com.onirutla.movflex.core.util.toCasts
import com.onirutla.movflex.core.util.toReviews
import com.onirutla.movflex.tv.core.local.TvLocalDataSource
import com.onirutla.movflex.tv.core.remote.TvRemoteDataSource
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvDetail
import com.onirutla.movflex.tv.domain.util.toEntity
import com.onirutla.movflex.tv.domain.util.toTv
import com.onirutla.movflex.tv.domain.util.toTvDetail
import com.onirutla.movflex.tv.domain.util.toTvs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class TvRepository @Inject constructor(
    private val remote: TvRemoteDataSource,
    private val local: TvLocalDataSource,
) {

    fun getTvPopularPaging(): Flow<PagingData<Tv>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getTvPopular(position)
            }
        },
    ).flow

    suspend fun getTvPopular(page: Int = 1): List<Tv> = remote.getTvPopular(page)
        .map { it.toTv() }

    fun getTvOnTheAirPaging(): Flow<PagingData<Tv>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getTvOnTheAir(position)
            }
        },
    ).flow

    suspend fun getTvOnTheAir(page: Int = 1): List<Tv> = remote.getTvOnTheAir(page)
        .map { it.toTv() }

    fun getTvTopRatedPaging(): Flow<PagingData<Tv>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getTvTopRated(position)
            }
        }
    ).flow

    suspend fun getTvTopRated(page: Int = 1): List<Tv> = remote.getTvTopRated(page)
        .map { it.toTv() }

    fun getTvAiringTodayPaging(): Flow<PagingData<Tv>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getTvAiringToday(position)
            }
        }
    ).flow

    suspend fun getTvAiringToday(page: Int = 1): List<Tv> = remote.getTvAiringToday(page)
        .map { it.toTv() }

    fun getTvDetail(id: Int): Flow<TvDetail> = flow {
        val tvDetail = remote.getTvDetail(id)?.toTvDetail() ?: TvDetail()
        emit(tvDetail)
    }.catch {
        Timber.d(it)
    }.filterNotNull()

    suspend fun setFavorite(tv: TvDetail) {
        val isFavorite = local.isInDb(tv.id)
        if (isFavorite == null)
            local.addToFavorite(tv.toEntity())
        else
            local.deleteFavorite(isFavorite)
    }

    fun getTvFavorite(): Flow<PagingData<Tv>> = local.getFavorite()
        .map { pagingData ->
            pagingData.map {
                it.toTv()
            }
        }

    suspend fun getTvCast(tvId: Int, page: Int = 1): List<Cast> = remote.getTvCast(tvId, page)
        .toCasts()

    fun getTvCastPaging(tvId: Int): Flow<PagingData<Cast>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getTvCast(tvId, position)
            }
        }
    ).flow

    suspend fun getTvReview(tvId: Int, page: Int = 1): List<Review> = remote.getTvReview(tvId, page)
        .toReviews()

    fun getTvReviewPaging(tvId: Int): Flow<PagingData<Review>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getTvReview(tvId, position)
            }
        }
    ).flow

    suspend fun getTvSimilar(tvId: Int, page: Int = 1): List<Tv> = remote.getTvSimilar(tvId, page)
        .toTvs()

    fun getTvSimilarPaging(tvId: Int): Flow<PagingData<Tv>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getTvSimilar(tvId, position)
            }
        }
    ).flow

    suspend fun getTvRecommendations(tvId: Int, page: Int = 1): List<Tv> =
        remote.getTvRecommendations(tvId, page)
            .toTvs()

    fun getTvRecommendationsPaging(tvId: Int): Flow<PagingData<Tv>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getTvRecommendations(tvId, position)
            }
        }
    ).flow

    suspend fun getTvSeason(tvId: Int): List<Season> = remote.getTvSeason(tvId)
        .toSeasons()

    fun observeFavoriteState(tvId: Int): Flow<Boolean> = local.observeFavoriteState(tvId)
}
