package com.onirutla.movflex.tv.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.onirutla.movflex.core.databinding.ItemContentVerticalBinding
import com.onirutla.movflex.tv.domain.model.Tv

class TvPagingVerticalAdapter(
    private inline val onClickListener: (view: View, movieContent: Tv) -> Unit,
) : PagingDataAdapter<Tv, TvPagingVerticalViewHolder>(TvComparator) {

    override fun onBindViewHolder(holder: TvPagingVerticalViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, onClickListener)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TvPagingVerticalViewHolder {
        val view = ItemContentVerticalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvPagingVerticalViewHolder(view)
    }
}
