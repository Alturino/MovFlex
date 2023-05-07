package com.onirutla.movflex.tv.ui.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.databinding.ItemHorizontalBinding
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.tv.domain.model.Tv

class TvHorizontalViewHolder(
    private val binding: ItemHorizontalBinding,
    private val onClickListener: (view: View, item: Tv) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Tv) {
        binding.apply {
            root.setOnClickListener {
                onClickListener(it, content)
            }
            content.apply {
                Glide.with(ivImage.context)
                    .load("$BASE_IMAGE_PATH$backdropPath")
                    .into(ivImage)
                    .clearOnDetach()
                    .onLoadFailed(
                        AppCompatResources.getDrawable(
                            ivImage.context,
                            R.drawable.ic_launcher_background
                        )
                    )

                tvTitle.text = name
                tvRating.text = root.context.getString(R.string.format_rating, (voteAverage / 2))
                tvYearRelease.text = firstAirDate
                tvVoteCount.text = voteCount.toString()
            }
        }
    }
}
