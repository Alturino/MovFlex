package com.onirutla.movflex.movie.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.ui.CastAdapter
import com.onirutla.movflex.core.ui.ReviewAdapter
import com.onirutla.movflex.core.util.Constants
import com.onirutla.movflex.movie.databinding.FragmentMovieDetailBinding
import com.onirutla.movflex.movie.ui.adapter.MovieHorizontalAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailViewModel by viewModels()

    private val args: MovieDetailFragmentArgs by navArgs()

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


        viewModel.apply {
            movieDetail.observe(viewLifecycleOwner) { movie ->
                binding.apply {
                    Glide.with(ivImage.context)
                        .load("${Constants.BASE_IMAGE_PATH}${movie.backdropPath}")
                        .into(ivImage)
                        .clearOnDetach()
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
            movieSimilar.observe(viewLifecycleOwner) {
                Timber.d("Similar: $it")
                if (it.isEmpty())
                    binding.rvSimilar.visibility = View.GONE
                similarAdapter.submitList(it)
            }
            movieCasts.observe(viewLifecycleOwner) {
                Timber.d("Casts: $it")
                castAdapter.submitList(it)
            }
            movieRecommendations.observe(viewLifecycleOwner) {
                Timber.d("Recommendations: $it")
                recommendationAdapter.submitList(it)
            }
            movieReviews.observe(viewLifecycleOwner) {
                Timber.d("Reviews: $it")
                reviewAdapter.submitList(it)
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) {
            Timber.d("isFavorite: $it")
            setFabState(it)
        }

        binding.apply {
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
