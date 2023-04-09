package com.onirutla.movflex.tv.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.onirutla.movflex.core.R
import com.onirutla.movflex.tv.databinding.FragmentTvDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailFragment : Fragment() {

    private var _binding: FragmentTvDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvDetailViewModel by viewModels()

    private val args: TvDetailFragmentArgs by navArgs()

    private var fabState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTvDetail(args.tvId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTvDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tvDetail.observe(viewLifecycleOwner) {
            binding.item = it
            fabState = it.isFavorite
            setFabState(it.isFavorite)
        }

        binding.fab.setOnClickListener {
            viewModel.setFavorite()
            fabState = !fabState
            setFabState(fabState)
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
