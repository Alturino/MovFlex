package com.onirutla.movflex.tv.ui.adapter.season

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.onirutla.movflex.core.domain.model.Episode
import com.onirutla.movflex.core.domain.model.Season
import com.onirutla.movflex.tv.databinding.ItemSeasonBinding

class SeasonAdapter(
    private val episodeClickListener: (View, Episode) -> Unit = { _, _ -> },
) : ListAdapter<Season, SeasonViewHolder>(SeasonComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val view = ItemSeasonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SeasonViewHolder(view, episodeClickListener)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = getItem(position)
        holder.bind(season)
    }
}

