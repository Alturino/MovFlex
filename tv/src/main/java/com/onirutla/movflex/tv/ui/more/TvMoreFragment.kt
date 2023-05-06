package com.onirutla.movflex.tv.ui.more

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
import com.onirutla.movflex.tv.databinding.FragmentTvMoreBinding
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvType.TV_AIRING_TODAY
import com.onirutla.movflex.tv.domain.model.TvType.TV_CASTS
import com.onirutla.movflex.tv.domain.model.TvType.TV_ON_THE_AIR
import com.onirutla.movflex.tv.domain.model.TvType.TV_POPULAR
import com.onirutla.movflex.tv.domain.model.TvType.TV_RECOMMENDATIONS
import com.onirutla.movflex.tv.domain.model.TvType.TV_REVIEWS
import com.onirutla.movflex.tv.domain.model.TvType.TV_SIMILAR
import com.onirutla.movflex.tv.domain.model.TvType.TV_TOP_RATED
import com.onirutla.movflex.tv.ui.adapter.TvPagingVerticalAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvMoreFragment : Fragment() {

    private var _binding: FragmentTvMoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvMoreViewModel by viewModels()

    private val args: TvMoreFragmentArgs by navArgs()

    private lateinit var tvMoreAdapter: TvPagingVerticalAdapter
    private lateinit var castMoreAdapter: CastPagingAdapter
    private lateinit var reviewMoreAdapter: ReviewPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTvByCategory(args.tvType)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTvMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvMoreAdapter = TvPagingVerticalAdapter { itemView, content ->
            itemView.findNavController().navigate(
                TvMoreFragmentDirections.actionTvMoreFragmentToTvDetailFragment(
                    content.id
                )
            )
        }

        castMoreAdapter = CastPagingAdapter { itemView, cast ->

        }

        reviewMoreAdapter = ReviewPagingAdapter { itemView, review ->

        }

        binding.apply {
            tvPaging.apply {
                adapter = tvMoreAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
            }
            toolbar.apply {
                title = args.tvType.value
                setNavigationOnClickListener { it.findNavController().navigateUp() }
            }
        }

        viewModel.tvMore.observe(viewLifecycleOwner) { pagingData ->
            when (args.tvType) {
                TV_POPULAR -> {
                    val tv = pagingData.map { it as Tv }
                    tvMoreAdapter.submitData(viewLifecycleOwner.lifecycle, tv)
                }

                TV_AIRING_TODAY -> {
                    val tv = pagingData.map { it as Tv }
                    tvMoreAdapter.submitData(viewLifecycleOwner.lifecycle, tv)
                }

                TV_TOP_RATED -> {
                    val tv = pagingData.map { it as Tv }
                    tvMoreAdapter.submitData(viewLifecycleOwner.lifecycle, tv)
                }

                TV_ON_THE_AIR -> {
                    val tv = pagingData.map { it as Tv }
                    tvMoreAdapter.submitData(viewLifecycleOwner.lifecycle, tv)
                }

                TV_SIMILAR -> {
                    val tv = pagingData.map { it as Tv }
                    tvMoreAdapter.submitData(viewLifecycleOwner.lifecycle, tv)
                }

                TV_RECOMMENDATIONS -> {
                    val tv = pagingData.map { it as Tv }
                    tvMoreAdapter.submitData(viewLifecycleOwner.lifecycle, tv)
                }

                TV_CASTS -> {
                    val cast = pagingData.map { it as Cast }
                    castMoreAdapter.submitData(viewLifecycleOwner.lifecycle, cast)
                    binding.tvPaging.adapter = castMoreAdapter
                }

                TV_REVIEWS -> {
                    val reviews = pagingData.map { it as Review }
                    reviewMoreAdapter.submitData(viewLifecycleOwner.lifecycle, reviews)
                    binding.tvPaging.adapter = reviewMoreAdapter
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
