package com.onirutla.movflex.favorite.movie

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.onirutla.movflex.favorite.databinding.FragmentFavoriteMovieBinding
import com.onirutla.movflex.movie.ui.adapter.MoviePagingVerticalAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMovieFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    private val vm: FavoriteMovieViewModel by viewModels()

    private val moviePagingAdapter by lazy {
        MoviePagingVerticalAdapter(
            onItemClickListener = { view, content ->
                view.findNavController()
                    .navigate(Uri.parse("movflex://main_nav/movie/${content.id}"))
            },
            onFavoriteClickListener = {
                vm.setFavorite(it)
            },
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.movieFavorite.observe(viewLifecycleOwner) {
            moviePagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        binding.favoriteMoviePaging.apply {
            adapter = moviePagingAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
