package com.onirutla.movflex.tv.ui.adapter.episode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.onirutla.movflex.core.domain.model.Episode
import com.onirutla.movflex.tv.databinding.ItemEpisodeBinding


class EpisodeAdapter(
    private val itemClickListener: (View, Episode) -> Unit = { _, _ -> },
) : ListAdapter<Episode, EpisodeViewHolder>(EpisodeComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = ItemEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EpisodeViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}
