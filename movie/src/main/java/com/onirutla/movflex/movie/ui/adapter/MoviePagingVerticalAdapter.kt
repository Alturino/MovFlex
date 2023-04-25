package com.onirutla.movflex.movie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.onirutla.movflex.movie.databinding.MovieVerticalBinding
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieComparator

class MoviePagingVerticalAdapter(
    private inline val onItemClickListener: (view: View, movieContent: Movie) -> Unit,
    private inline val onFavoriteClickListener: (Movie) -> Unit,
) : PagingDataAdapter<Movie, MoviePagingVerticalViewHolder>(MovieComparator) {

    override fun onBindViewHolder(holder: MoviePagingVerticalViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(
                content = item,
                itemClickListener = onItemClickListener,
                favoriteClickListener = onFavoriteClickListener
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MoviePagingVerticalViewHolder {
        val view = MovieVerticalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviePagingVerticalViewHolder(view)
    }
}
