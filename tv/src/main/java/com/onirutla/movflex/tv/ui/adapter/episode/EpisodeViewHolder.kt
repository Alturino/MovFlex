package com.onirutla.movflex.tv.ui.adapter.episode

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.domain.model.Episode
import com.onirutla.movflex.core.util.loadImage
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
            ivImage.loadImage(item.stillPath)
            tvName.text = item.name
            tvOverview.text = item.overview
            tvEpisodeNumber.text = "Episode ${item.episodeNumber}"
        }
    }
}
