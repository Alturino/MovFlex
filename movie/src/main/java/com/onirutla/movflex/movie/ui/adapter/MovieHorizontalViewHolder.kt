package com.onirutla.movflex.movie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.movie.databinding.MovieHorizontalBinding
import com.onirutla.movflex.movie.domain.model.Movie

class MovieHorizontalViewHolder(
    private val binding: MovieHorizontalBinding,
    private val onClickListener: (view: View, item: Movie) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Movie) {
        binding.apply {
            root.setOnClickListener {
                onClickListener(it, content)
            }
            content.apply {
                Glide.with(itemImage.context)
                    .load("$BASE_IMAGE_PATH$backdropPath")
                    .into(itemImage)
                    .clearOnDetach()

                itemTitle.text = title
                itemRating.text = root.context.getString(R.string.format_rating, (voteAverage / 2))
                itemYearRelease.text = releaseDate
                itemGenre.text = genres.first().name
                itemVoteCount.text = voteCount.toString()
            }
        }
    }
}
