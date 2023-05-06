package com.onirutla.movflex.tv.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.ui.cast.CastAdapter
import com.onirutla.movflex.core.ui.review.ReviewAdapter
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.tv.databinding.FragmentTvDetailBinding
import com.onirutla.movflex.tv.domain.model.TvType
import com.onirutla.movflex.tv.ui.adapter.TvHorizontalAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TvDetailFragment : Fragment() {

    private var _binding: FragmentTvDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvDetailViewModel by viewModels()

    private val args: TvDetailFragmentArgs by navArgs()

    private fun navigator(view: View, tvType: TvType, tvId: Int) {
        view.findNavController().navigate(
            TvDetailFragmentDirections.actionTvDetailFragmentToTvMoreFragment(
                tvType,
                tvId
            )
        )
    }

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

        val recommendationAdapter = TvHorizontalAdapter { itemView, tv ->
            itemView.findNavController()
                .navigate(TvDetailFragmentDirections.actionTvDetailFragmentSelf(tv.id))
        }

        val reviewAdapter = ReviewAdapter { _, _ -> }

        val castAdapter = CastAdapter { _, _ -> }

        val similarAdapter = TvHorizontalAdapter { itemView, tv ->
            itemView.findNavController()
                .navigate(TvDetailFragmentDirections.actionTvDetailFragmentSelf(tv.id))
        }

        binding.apply {
            tvCastsSeeMore.setOnClickListener {
                navigator(it, TvType.TV_CASTS, args.tvId)
            }
            tvReviewSeeMore.setOnClickListener {
                navigator(it, TvType.TV_REVIEWS, args.tvId)
            }
            tvRecommendationsSeeMore.setOnClickListener {
                navigator(it, TvType.TV_RECOMMENDATIONS, args.tvId)
            }
            tvSimilarSeeMore.setOnClickListener {
                navigator(it, TvType.TV_SIMILAR, args.tvId)
            }
            rvCasts.apply {
                adapter = castAdapter
            }
            rvRecommendation.apply {
                adapter = recommendationAdapter
            }
            rvReviews.apply {
                adapter = reviewAdapter
            }
            rvSimilar.apply {
                adapter = similarAdapter
            }
        }

        viewModel.tvDetail.observe(viewLifecycleOwner) { tv ->
            binding.apply {
                Glide.with(ivImage.context)
                    .load("${BASE_IMAGE_PATH}${tv.backdropPath}")
                    .into(ivImage)
                    .clearOnDetach()
                tvTitle.text = tv.name
                tvOriginalTitle.text = tv.originalName
                tvRating.text = requireContext().getString(
                    R.string.format_rating,
                    (tv.voteAverage / 2)
                )
                tvVoteCount.text = requireContext().getString(
                    R.string.format_vote_count,
                    tv.voteCount,
                )
                tvOverview.text = tv.overview
                tvGenre.text = tv.genres
                fab.setOnClickListener {
                    viewModel.setFavorite(tv)
                }
            }
        }

        viewModel.tvSimilar.observe(viewLifecycleOwner) {
            Timber.d("Similar: $it")
            similarAdapter.submitList(it)
        }

        viewModel.tvCasts.observe(viewLifecycleOwner) {
            Timber.d("Casts: $it")
            castAdapter.submitList(it)
        }

        viewModel.tvRecommendations.observe(viewLifecycleOwner) {
            Timber.d("Recommendations: $it")
            recommendationAdapter.submitList(it)
        }

        viewModel.tvReviews.observe(viewLifecycleOwner) {
            Timber.d("Reviews: $it")
            reviewAdapter.submitList(it)
        }

        viewModel.tvSeason.observe(viewLifecycleOwner) {
            Timber.d("Season: $it")
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) {
            Timber.d("isFavorite: $it")
            setFabState(it)
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
