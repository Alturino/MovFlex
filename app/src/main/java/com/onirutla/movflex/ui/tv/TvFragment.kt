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
import com.onirutla.movflex.domain.model.type.TvType
import com.onirutla.movflex.ui.SeeMoreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvViewModel by viewModels()

    private var seeMoreAdapter: SeeMoreAdapter? = null

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

        seeMoreAdapter = SeeMoreAdapter(itemClickListener = { itemView, itemId ->
            itemView.findNavController().navigate(
                TvFragmentDirections.actionTvFragmentToTvDetailFragment(itemId)
            )
        }) { seeMoreView, category ->
            when (category) {
                TvType.TV_POPULAR.value -> navigator(seeMoreView, TvType.TV_POPULAR)
                TvType.TV_TOP_RATED.value -> navigator(seeMoreView, TvType.TV_TOP_RATED)
                TvType.TV_ON_THE_AIR.value -> navigator(seeMoreView, TvType.TV_ON_THE_AIR)
                TvType.TV_AIRING_TODAY.value -> navigator(seeMoreView, TvType.TV_AIRING_TODAY)
            }
        }

        viewModel.tvHome.observe(viewLifecycleOwner) {
            seeMoreAdapter?.submitList(it)
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
        seeMoreAdapter = null
    }
}