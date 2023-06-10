package com.onirutla.movflex.movie.ui.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.ui.cast.CastPagingAdapter
import com.onirutla.movflex.core.ui.review.ReviewPagingAdapter
import com.onirutla.movflex.movie.databinding.FragmentMovieMoreBinding
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_CASTS
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_NOW_PLAYING
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_POPULAR
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_RECOMMENDATIONS
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_REVIEWS
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_SIMILAR
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_TOP_RATED
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_UPCOMING
import com.onirutla.movflex.movie.ui.adapter.MoviePagingVerticalAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieMoreFragment : Fragment() {

    private var _binding: FragmentMovieMoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieMoreViewModel by viewModels()

    private val args: MovieMoreFragmentArgs by navArgs()

    private lateinit var movieMoreAdapter: MoviePagingVerticalAdapter
    private lateinit var reviewMoreAdapter: ReviewPagingAdapter
    private lateinit var castMoreAdapter: CastPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val category = args.movieType
        viewModel.movieId = args.movieId
        viewModel.getMovieByCategory(category)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieMoreAdapter = MoviePagingVerticalAdapter(
            onItemClickListener = { itemView, content ->
                itemView.findNavController().navigate(
                    MovieMoreFragmentDirections.actionMovieMoreFragmentToMovieDetailFragment(content.id)
                )
            },
        )

        reviewMoreAdapter = ReviewPagingAdapter(
            onItemClickListener = { itemView, review ->

            }
        )

        castMoreAdapter = CastPagingAdapter(
            onItemClickListener = { itemView, cast ->

            },
        )

        binding.moviePaging.apply {
            adapter = movieMoreAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.toolbar.apply {
            title = requireContext().getString(args.movieType.value)
            setOnClickListener { it.findNavController().navigateUp() }
        }

        viewModel.movieMore.observe(viewLifecycleOwner) { pagingData ->
            when (args.movieType) {
                MOVIE_UPCOMING -> {
                    val movie = pagingData.map { it as Movie }
                    movieMoreAdapter.submitData(viewLifecycleOwner.lifecycle, movie)
                }

                MOVIE_TOP_RATED -> {
                    val movie = pagingData.map { it as Movie }
                    movieMoreAdapter.submitData(viewLifecycleOwner.lifecycle, movie)
                }

                MOVIE_NOW_PLAYING -> {
                    val movie = pagingData.map { it as Movie }
                    movieMoreAdapter.submitData(viewLifecycleOwner.lifecycle, movie)
                }

                MOVIE_POPULAR -> {
                    val movie = pagingData.map { it as Movie }
                    movieMoreAdapter.submitData(viewLifecycleOwner.lifecycle, movie)
                }

                MOVIE_RECOMMENDATIONS -> {
                    val movie = pagingData.map { it as Movie }
                    movieMoreAdapter.submitData(viewLifecycleOwner.lifecycle, movie)
                }

                MOVIE_SIMILAR -> {
                    val movie = pagingData.map { it as Movie }
                    movieMoreAdapter.submitData(viewLifecycleOwner.lifecycle, movie)
                }

                MOVIE_REVIEWS -> {
                    val review = pagingData.map { it as Review }
                    reviewMoreAdapter.submitData(viewLifecycleOwner.lifecycle, review)
                    binding.moviePaging.adapter = reviewMoreAdapter
                }

                MOVIE_CASTS -> {
                    val cast = pagingData.map { it as Cast }
                    castMoreAdapter.submitData(viewLifecycleOwner.lifecycle, cast)
                    binding.moviePaging.adapter = castMoreAdapter
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
