package com.onirutla.movflex.movie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.onirutla.movflex.movie.databinding.MovieHorizontalBinding
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieComparator

class MovieHorizontalAdapter(
    private inline val onClickListener: (view: View, item: Movie) -> Unit,
) : ListAdapter<Movie, MovieHorizontalViewHolder>(MovieComparator) {

    override fun onBindViewHolder(holder: MovieHorizontalViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieHorizontalViewHolder {
        val view = MovieHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieHorizontalViewHolder(view, onClickListener)
    }

}

