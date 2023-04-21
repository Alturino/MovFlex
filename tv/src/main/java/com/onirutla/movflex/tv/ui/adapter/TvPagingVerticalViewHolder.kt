package com.onirutla.movflex.tv.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.databinding.ItemContentVerticalBinding
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.tv.domain.model.Tv

class TvPagingVerticalViewHolder(
    private val binding: ItemContentVerticalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Tv, onClickListener: (view: View, movieContent: Tv) -> Unit) {
        binding.apply {
            root.setOnClickListener {
                onClickListener(it, content)
            }

            content.apply {
                Glide.with(itemImage.context)
                    .load("$BASE_IMAGE_PATH$backdropPath")
                    .into(itemImage)
                    .clearOnDetach()

//                itemGenre.text = genres.first().name
                itemSynopsis.text = overview
                itemYearRelease.text = firstAirDate
                itemTitle.text = name
            }
        }
    }

}
