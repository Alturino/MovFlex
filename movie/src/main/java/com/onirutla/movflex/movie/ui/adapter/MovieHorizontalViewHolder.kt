package com.onirutla.movflex.movie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.databinding.ItemHorizontalBinding
import com.onirutla.movflex.core.util.loadImage
import com.onirutla.movflex.movie.domain.model.Movie

class MovieHorizontalViewHolder(
    private val binding: ItemHorizontalBinding,
    private val onClickListener: (view: View, item: Movie) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie) {
        binding.apply {
            root.setOnClickListener {
                onClickListener(it, item)
            }
            item.apply {
                ivImage.loadImage(backdropPath)
                tvTitle.text = title
                tvRating.text = root.context.getString(R.string.format_rating, (voteAverage / 2))
                tvYearRelease.text = releaseDate
//                tvGenre.text = genres.first().name
                tvVoteCount.text = voteCount.toString()
            }
        }
    }
}
