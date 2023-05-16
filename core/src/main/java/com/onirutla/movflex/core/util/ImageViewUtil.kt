package com.onirutla.movflex.core.util

import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import coil.imageLoader
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH

fun ImageView.loadImage(
    url: String,
    onStart: (request: ImageRequest) -> Unit = {},
    onCancel: (request: ImageRequest) -> Unit = {},
    onError: (request: ImageRequest, result: ErrorResult) -> Unit = { _, _ -> },
    onSuccess: (request: ImageRequest, result: SuccessResult) -> Unit = { _, _ -> }
) {
    if (url.isBlank() or url.isEmpty())
        return

    val request = ImageRequest.Builder(context)
        .data("$BASE_IMAGE_PATH$url")
        .target(this)
        .listener(
            onStart = onStart,
            onCancel = onCancel,
            onError = onError,
            onSuccess = onSuccess
        )
        .build()

    context.imageLoader.enqueue(request)
}
