package com.onirutla.movflex.core.ui.cast

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.databinding.ItemCastVerticalBinding
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH

class CastPagingViewHolder(
    private val binding: ItemCastVerticalBinding,
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
