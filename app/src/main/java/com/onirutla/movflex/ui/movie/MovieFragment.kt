package com.onirutla.movflex.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.onirutla.movflex.core.domain.model.type.MovieType
import com.onirutla.movflex.core.ui.SeeMoreAdapter
import com.onirutla.movflex.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    private var seeMoreAdapter: SeeMoreAdapter? = null

    private fun navigator(view: View, movieType: MovieType) {
        view.findNavController()
            .navigate(MovieFragmentDirections.actionMovieFragmentToMovieMoreFragment(movieType))
    }

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seeMoreAdapter = SeeMoreAdapter(itemClickListener = { itemView, itemId ->
            itemView.findNavController()
                .navigate(
                    MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(itemId)
                )
        }) { moreView, category ->
            when (category) {
                MovieType.MOVIE_NOW_PLAYING.value -> {
                    navigator(moreView, MovieType.MOVIE_NOW_PLAYING)
                }
                MovieType.MOVIE_POPULAR.value -> {
                    navigator(moreView, MovieType.MOVIE_POPULAR)
                }
                MovieType.MOVIE_TOP_RATED.value -> {
                    navigator(moreView, MovieType.MOVIE_TOP_RATED)
                }
                MovieType.MOVIE_UPCOMING.value -> {
                    navigator(moreView, MovieType.MOVIE_UPCOMING)
                }
            }
        }

        observeData()

        binding.movieHomeList.apply {
            adapter = seeMoreAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movie.collect {
                    seeMoreAdapter?.submitList(it.toMutableList())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        seeMoreAdapter = null
    }
}