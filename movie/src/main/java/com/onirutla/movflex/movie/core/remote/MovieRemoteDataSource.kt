package com.onirutla.movflex.movie.core.remote

import com.onirutla.movflex.core.data.source.remote.response.CastResponse
import com.onirutla.movflex.core.data.source.remote.response.ReviewResponse
import com.onirutla.movflex.movie.core.remote.model.MovieDetailResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import timber.log.Timber
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieApiService: MovieApiService,
) {

    suspend fun getMoviePopular(page: Int = 1): List<MovieResponse> = try {
        val response = movieApiService.getMoviePopular(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d("$e")
        emptyList()
    }

    suspend fun getMovieNowPlaying(page: Int = 1): List<MovieResponse> = try {
        val response = movieApiService.getMovieNowPlaying(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d("$e")
        emptyList()
    }

    suspend fun getMovieTopRated(page: Int = 1): List<MovieResponse> = try {
        val response = movieApiService.getMovieTopRated(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d("$e")
        emptyList()
    }

    suspend fun getMovieDetail(id: Int): MovieDetailResponse? = try {
        val response = movieApiService.getMovieDetail(id)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!
        } else {
            Timber.d("${response.errorBody()}")
            null
        }
    } catch (e: Exception) {
        Timber.d("$e")
        null
    }

    suspend fun getMovieUpcoming(page: Int = 1): List<MovieResponse> = try {
        val response = movieApiService.getMovieUpcoming(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d("$e")
        emptyList()
    }

    suspend fun getMovieSimilar(movieId: Int, page: Int = 1): List<MovieResponse> = try {
        val response = movieApiService.getMovieSimilar(movieId, page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d("$e")
        emptyList()
    }

    suspend fun getMovieRecommendations(movieId: Int, page: Int = 1): List<MovieResponse> = try {
        val response = movieApiService.getMovieRecommendations(
            movieId = movieId,
            page = page,
        )
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d("$e")
        emptyList()
    }

    suspend fun getMovieCasts(movieId: Int, page: Int = 1): List<CastResponse> = try {
        val response = movieApiService.getMovieCasts(movieId, page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.cast.orEmpty()
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d("$e")
        emptyList()
    }

    suspend fun getMovieReviews(movieId: Int, page: Int = 1): List<ReviewResponse> = try {
        val response = movieApiService.getMovieReviews(
            movieId = movieId,
            page = page,
        )
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.reviews
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d("$e")
        emptyList()
    }
}
