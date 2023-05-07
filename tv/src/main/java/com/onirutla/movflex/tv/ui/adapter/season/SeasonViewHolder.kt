package com.onirutla.movflex.tv.ui.adapter.season

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.domain.model.Episode
import com.onirutla.movflex.core.domain.model.Season
import com.onirutla.movflex.tv.databinding.ItemSeasonBinding
import com.onirutla.movflex.tv.ui.adapter.episode.EpisodeAdapter

class SeasonViewHolder(
    private val binding: ItemSeasonBinding,
    private val episodeClickListener: (View, Episode) -> Unit = { _, _ -> },
) : RecyclerView.ViewHolder(binding.root) {

    private val episodeAdapter = EpisodeAdapter(episodeClickListener)

    fun bind(item: Season) {
        binding.apply {
            episodeAdapter.submitList(item.episodes)
            rvEpisode.apply {
                adapter = episodeAdapter
            }
            tvSeason.text = "Season ${item.seasonNumber}"
        }
    }
}
