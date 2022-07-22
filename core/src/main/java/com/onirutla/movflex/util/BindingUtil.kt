package com.onirutla.movflex.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.onirutla.movflex.util.Constants.BASE_IMAGE_PATH

@BindingAdapter("load_image")
fun loadImage(imageView: ImageView, any: Any?) {
    any?.let { Glide.with(imageView).load("$BASE_IMAGE_PATH$any").into(imageView).clearOnDetach() }
}