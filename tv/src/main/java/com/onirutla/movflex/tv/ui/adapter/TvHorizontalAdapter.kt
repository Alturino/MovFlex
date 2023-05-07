package com.onirutla.movflex.tv.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.onirutla.movflex.core.databinding.ItemHorizontalBinding
import com.onirutla.movflex.tv.domain.model.Tv

class TvHorizontalAdapter(
    private inline val onClickListener: (view: View, item: Tv) -> Unit,
) : ListAdapter<Tv, TvHorizontalViewHolder>(TvComparator) {

    override fun onBindViewHolder(holder: TvHorizontalViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TvHorizontalViewHolder {
        val view = ItemHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvHorizontalViewHolder(view, onClickListener)
    }

}

