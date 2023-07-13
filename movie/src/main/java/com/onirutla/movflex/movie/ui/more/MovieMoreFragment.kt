package com.onirutla.movflex.movie.ui.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.onirutla.movflex.core.ui.cast.CastPagingAdapter
import com.onirutla.movflex.core.ui.review.ReviewPagingAdapter
import com.onirutla.movflex.movie.databinding.FragmentMovieMoreBinding
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
            title = args.movieType.value
            setOnClickListener { it.findNavController().navigateUp() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
