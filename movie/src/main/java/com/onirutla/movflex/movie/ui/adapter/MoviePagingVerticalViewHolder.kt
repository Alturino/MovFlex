package com.onirutla.movflex.movie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.movie.databinding.MovieVerticalBinding
import com.onirutla.movflex.movie.domain.model.Movie

class MoviePagingVerticalViewHolder(
    private val binding: MovieVerticalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Movie, onClickListener: (view: View, movieContent: Movie) -> Unit) {
        binding.apply {
            root.setOnClickListener {
                onClickListener(it, content)
            }

            content.apply {
                Glide.with(itemImage.context)
                    .load("$BASE_IMAGE_PATH$backdropPath")
                    .into(itemImage)
                    .clearOnDetach()

                itemGenre.text = genres.first().name
                itemSynopsis.text = overview
                itemYearRelease.text = releaseDate
                itemTitle.text = title
            }
        }
    }

}
