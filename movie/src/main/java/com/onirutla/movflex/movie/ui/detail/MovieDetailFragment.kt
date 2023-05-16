package com.onirutla.movflex.movie.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.ui.cast.CastAdapter
import com.onirutla.movflex.core.ui.review.ReviewAdapter
import com.onirutla.movflex.core.util.loadImage
import com.onirutla.movflex.movie.databinding.FragmentMovieDetailBinding
import com.onirutla.movflex.movie.domain.model.MovieType
import com.onirutla.movflex.movie.ui.adapter.MovieHorizontalAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailViewModel by viewModels()

    private val args: MovieDetailFragmentArgs by navArgs()

    private fun navigator(view: View, movieType: MovieType, movieId: Int) {
        view.findNavController().navigate(
            MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieMoreFragment(
                movieType,
                movieId
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieDetail(args.movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recommendationAdapter = MovieHorizontalAdapter { itemView, movie ->
            itemView.findNavController()
                .navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentSelf(movie.id))
        }

        val reviewAdapter = ReviewAdapter { _, _ -> }

        val castAdapter = CastAdapter { _, _ -> }

        val similarAdapter = MovieHorizontalAdapter { itemView, movie ->
            itemView.findNavController()
                .navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentSelf(movie.id))
        }

        binding.apply {
            tvCastsSeeMore.setOnClickListener {
                navigator(it, MovieType.MOVIE_CASTS, args.movieId)
            }
            tvReviewSeeMore.setOnClickListener {
                navigator(it, MovieType.MOVIE_REVIEWS, args.movieId)
            }
            tvRecommendationsSeeMore.setOnClickListener {
                navigator(it, MovieType.MOVIE_RECOMMENDATIONS, args.movieId)
            }
            tvSimilarSeeMore.setOnClickListener {
                navigator(it, MovieType.MOVIE_SIMILAR, args.movieId)
            }
            rvCasts.apply {
                adapter = castAdapter
            }
            rvRecommendation.apply {
                adapter = recommendationAdapter
            }
            rvReviews.apply {
                adapter = reviewAdapter
            }
            rvSimilar.apply {
                adapter = similarAdapter
            }
        }

        viewModel.movieDetail.observe(viewLifecycleOwner) { movie ->
            binding.apply {
                ivImage.loadImage(movie.backdropPath)
                tvTitle.text = movie.title
                tvOriginalTitle.text = movie.originalTitle
                tvRating.text = requireContext().getString(
                    R.string.format_rating,
                    (movie.voteAverage / 2)
                )
                tvVoteCount.text = requireContext().getString(
                    R.string.format_vote_count,
                    movie.voteCount,
                )
                tvOverview.text = movie.overview
                tvGenre.text = movie.genre
                fab.setOnClickListener {
                    viewModel.setFavorite(movie)
                }
            }
        }
        viewModel.movieSimilar.observe(viewLifecycleOwner) {
            Timber.d("Similar: $it")
            similarAdapter.submitList(it)
        }
        viewModel.movieCasts.observe(viewLifecycleOwner) {
            Timber.d("Casts: $it")
            castAdapter.submitList(it)
        }
        viewModel.movieRecommendations.observe(viewLifecycleOwner) {
            Timber.d("Recommendations: $it")
            recommendationAdapter.submitList(it)
        }
        viewModel.movieReviews.observe(viewLifecycleOwner) {
            Timber.d("Reviews: $it")
            reviewAdapter.submitList(it)
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) {
            Timber.d("isFavorite: $it")
            setFabState(it)
        }

    }

    private fun setFabState(state: Boolean) {
        if (state) {
            binding.fab.setImageResource(R.drawable.ic_favorite_24)
        } else {
            binding.fab.setImageResource(R.drawable.ic_favorite_border_24)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
