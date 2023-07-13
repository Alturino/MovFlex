package com.onirutla.movflex.movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.movie.databinding.FragmentMovieBinding
import com.onirutla.movflex.movie.domain.model.MovieType
import com.onirutla.movflex.movie.ui.adapter.MovieSeeMoreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    private var seeMoreAdapter: MovieSeeMoreAdapter? = null

    private fun navigator(view: View, movieType: MovieType) {
        view.findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToMovieMoreFragment(movieType)
        )
    }

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seeMoreAdapter = MovieSeeMoreAdapter(
            itemClickListener = { itemView, itemId ->
                itemView.findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(itemId)
                )
            },
            seeMoreClickListener = { moreView, category ->
                when (category) {
                    MovieType.MOVIE_NOW_PLAYING.value -> navigator(
                        moreView,
                        MovieType.MOVIE_NOW_PLAYING
                    )

                    MovieType.MOVIE_POPULAR.value -> navigator(
                        moreView,
                        MovieType.MOVIE_POPULAR
                    )

                    MovieType.MOVIE_TOP_RATED.value -> navigator(
                        moreView,
                        MovieType.MOVIE_TOP_RATED
                    )

                    MovieType.MOVIE_UPCOMING.value -> navigator(
                        moreView,
                        MovieType.MOVIE_UPCOMING
                    )
                }
            },
            rvViewPool = RecyclerView.RecycledViewPool()
        )

        viewModel.movie.observe(viewLifecycleOwner) {
            seeMoreAdapter?.submitList(it)
        }

        binding.movieHomeList.apply {
            adapter = seeMoreAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        seeMoreAdapter = null
    }
}
