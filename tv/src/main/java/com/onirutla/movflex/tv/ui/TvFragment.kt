package com.onirutla.movflex.tv.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.tv.databinding.FragmentTvBinding
import com.onirutla.movflex.tv.domain.model.TvType
import com.onirutla.movflex.tv.ui.adapter.TvSeeMoreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvViewModel by viewModels()

    private val seeMoreAdapter: TvSeeMoreAdapter by lazy {
        TvSeeMoreAdapter(
            itemClickListener = { itemView, itemId ->
                itemView.findNavController().navigate(
                    TvFragmentDirections.actionTvFragmentToTvDetailFragment(itemId)
                )
            },
            seeMoreClickListener = { seeMoreView, category ->
                when (category) {
                    requireContext().getString(TvType.TV_POPULAR.value) -> navigator(
                        seeMoreView,
                        TvType.TV_POPULAR
                    )

                    requireContext().getString(TvType.TV_TOP_RATED.value) -> navigator(
                        seeMoreView,
                        TvType.TV_TOP_RATED
                    )

                    requireContext().getString(TvType.TV_ON_THE_AIR.value) -> navigator(
                        seeMoreView,
                        TvType.TV_ON_THE_AIR
                    )

                    requireContext().getString(TvType.TV_AIRING_TODAY.value) -> navigator(
                        seeMoreView,
                        TvType.TV_AIRING_TODAY
                    )
                }
            },
            rvViewPool = RecyclerView.RecycledViewPool()
        )
    }

    private fun navigator(view: View, tvType: TvType) {
        view.findNavController().navigate(
            TvFragmentDirections.actionTvFragmentToTvMoreFragment(tvType)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tvHome.observe(viewLifecycleOwner) {
            seeMoreAdapter.submitList(it.toMutableList())
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
