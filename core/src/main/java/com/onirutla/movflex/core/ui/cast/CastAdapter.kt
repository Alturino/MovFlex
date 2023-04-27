package com.onirutla.movflex.core.ui.cast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.onirutla.movflex.core.databinding.ItemCastHorizontalBinding
import com.onirutla.movflex.core.domain.model.Cast

class CastAdapter(
    private val clickListener: (View, Cast) -> Unit,
) : ListAdapter<Cast, CastListViewHolder>(CastComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastListViewHolder {
        val view = ItemCastHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return CastListViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: CastListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
