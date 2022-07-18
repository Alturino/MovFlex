package com.onirutla.movflex.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.onirutla.movflex.databinding.FragmentTvBinding
import com.onirutla.movflex.domain.model.TvType
import com.onirutla.movflex.ui.adapter.SeeMoreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvViewModel by viewModels()

    private val seeMoreAdapter by lazy {
        SeeMoreAdapter(itemClickListener = { view, itemId ->
            view.findNavController().navigate(
                TvFragmentDirections.actionTvFragmentToTvDetailFragment(itemId)
            )
        }) { view, category ->
            when (category) {
                TvType.TV_POPULAR.value -> navigator(view, TvType.TV_POPULAR)
                TvType.TV_TOP_RATED.value -> navigator(view, TvType.TV_TOP_RATED)
                TvType.TV_ON_THE_AIR.value -> navigator(view, TvType.TV_ON_THE_AIR)
                TvType.TV_AIRING_TODAY.value -> navigator(view, TvType.TV_AIRING_TODAY)
            }
        }
    }

    private fun navigator(view: View, tvType: TvType) {
        view.findNavController().navigate(
            TvFragmentDirections.actionTvFragmentToTvMoreFragment(tvType)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tvHome.observe(viewLifecycleOwner) {
            seeMoreAdapter.submitList(it)
        }

        binding.tvHomeList.apply {
            adapter = seeMoreAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}