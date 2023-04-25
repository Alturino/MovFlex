package com.onirutla.movflex.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.databinding.ItemCastsBinding
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.util.Constants


class CastAdapter(
    private val clickListener: (View, Cast) -> Unit,
) : ListAdapter<Cast, CastViewHolder>(CastComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = ItemCastsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return CastViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}


class CastViewHolder(
    private val binding: ItemCastsBinding,
    private val onClickListener: (View, Cast) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Cast) {
        binding.apply {
            root.setOnClickListener { onClickListener(it, item) }
            castName.text = item.name
            characterName.text = item.character
            Glide.with(ivAvatar.context)
                .load("${Constants.BASE_IMAGE_PATH}${item.profilePath}")
                .into(ivAvatar)
                .clearOnDetach()
        }
    }
}

object CastComparator : DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean =
        oldItem.id == newItem.id && oldItem.castId == newItem.castId && oldItem.creditId == newItem.creditId

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean =
        oldItem.popularity == newItem.popularity && oldItem.character == newItem.character

}
