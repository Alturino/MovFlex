package com.onirutla.movflex.movie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.ItemVerticalBinding
import com.onirutla.movflex.core.util.loadImage
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.core.R as coreR

class MoviePagingVerticalViewHolder(
    private val binding: ItemVerticalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Movie,
        itemClickListener: (View, Movie) -> Unit = { _, _ -> },
    ) {
        binding.apply {
            root.setOnClickListener {
                itemClickListener(it, item)
            }

            item.apply {
                ivImage.loadImage(backdropPath)
                tvRating.text = root.context
                    .getString(coreR.string.format_rating, (voteAverage.toFloat() / 2f))

                tvGenre.text = genres
                tvSynopsis.text = overview
                tvYearRelease.text = releaseDate
                tvTitle.text = title
            }
        }
    }

}
