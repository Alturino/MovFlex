package com.onirutla.movflex.tv.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.ItemVerticalBinding
import com.onirutla.movflex.core.util.loadImage
import com.onirutla.movflex.tv.domain.model.Tv

class TvPagingVerticalViewHolder(
    private val binding: ItemVerticalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Tv, onClickListener: (view: View, movieContent: Tv) -> Unit) {
        binding.apply {
            root.setOnClickListener {
                onClickListener(it, content)
            }

            content.apply {
                ivImage.loadImage(backdropPath)
//                Genre.text = genres.first().name
                tvSynopsis.text = overview
                tvYearRelease.text = firstAirDate
                tvTitle.text = name
            }
        }
    }

}
