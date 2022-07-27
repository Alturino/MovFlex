package com.onirutla.movflex.core.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH

@BindingAdapter("load_image")
fun loadImage(imageView: ImageView, any: Any?) {
    any?.let { Glide.with(imageView.context).load("$BASE_IMAGE_PATH$any").into(imageView).clearOnDetach() }
}