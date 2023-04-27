package com.onirutla.movflex.core.ui.cast

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.databinding.ItemCastHorizontalBinding
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH

class CastListViewHolder(
    private val binding: ItemCastHorizontalBinding,
    private val onClickListener: (View, Cast) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Cast) {
        binding.apply {
            root.setOnClickListener { onClickListener(it, item) }
            castName.text = item.name
            characterName.text = item.character
            Glide.with(ivAvatar.context)
                .load("$BASE_IMAGE_PATH${item.profilePath}")
                .into(ivAvatar)
                .clearOnDetach()
        }
    }
}
