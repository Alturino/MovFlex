package com.onirutla.movflex.tv.ui.adapter.episode

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.domain.model.Episode
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.tv.databinding.ItemEpisodeBinding

class EpisodeViewHolder(
    private val binding: ItemEpisodeBinding,
    private val itemClickListener: (View, Episode) -> Unit = { _, _ -> },
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Episode) {
        binding.apply {
            root.setOnClickListener {
                itemClickListener(it, item)
            }
            Glide.with(ivImage.context)
                .load("$BASE_IMAGE_PATH${item.stillPath}")
                .into(ivImage)
                .clearOnDetach()
            tvName.text = item.name
            tvOverview.text = item.overview
            tvEpisodeNumber.text = "Episode ${item.episodeNumber}"
        }
    }
}
