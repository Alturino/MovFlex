package com.onirutla.movflex.tv.core.remote

import com.onirutla.movflex.core.data.source.remote.response.CastResponse
import com.onirutla.movflex.core.data.source.remote.response.ReviewResponse
import com.onirutla.movflex.core.data.source.remote.response.SeasonResponse
import com.onirutla.movflex.tv.core.remote.model.TvDetailResponse
import com.onirutla.movflex.tv.core.remote.model.TvResponse
import timber.log.Timber
import javax.inject.Inject

class TvRemoteDataSource @Inject constructor(
    private val apiService: TvApiService,
) {

    suspend fun getTvPopular(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvPopular(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvOnTheAir(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvOnTheAir(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvTopRated(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvTopRated(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvDetail(tvId: Int): TvDetailResponse? = try {
        val response = apiService.getTvDetail(tvId)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!
        } else {
            Timber.d("${response.body()}")
            null
        }
    } catch (e: Exception) {
        Timber.d(e)
        null
    }

    suspend fun getTvAiringToday(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvAiringToday(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvCast(tvId: Int, page: Int = 1): List<CastResponse> = try {
        val response = apiService.getTvCast(tvId, page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.cast.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvReview(tvId: Int, page: Int = 1): List<ReviewResponse> = try {
        val response = apiService.getTvReviews(tvId, page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.reviews
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvSimilar(tvId: Int, page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvSimilar(tvId, page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvRecommendations(tvId: Int, page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvRecommendations(tvId, page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvSeason(tvId: Int): List<SeasonResponse> = try {
        val tvDetailResponse = getTvDetail(tvId)
        if (tvDetailResponse?.numberOfSeasons == null)
            emptyList()
        else {
            val seasons = mutableListOf<SeasonResponse>()
            for (i in 1..tvDetailResponse.numberOfSeasons) {
                val response = apiService.getTvSeason(tvId, i)
                if (response.isSuccessful) {
                    val seasonResponse = response.body()!!
                    Timber.d("iteration $i: $seasonResponse")
                    seasons.add(seasonResponse)
                } else {
                    Timber.d("${response.errorBody()}")
                }
                continue
            }
            seasons
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }
}
