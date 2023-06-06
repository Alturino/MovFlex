package com.onirutla.movflex.core.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.onirutla.movflex.core.domain.model.Cast

class CastProvider(
    override val values: Sequence<Cast> = sequenceOf(
        Cast(
            profilePath = "/cbEWkQM0FS9vzv07JFErCk0YKkx.jpg",
            name = "Keras",
            character = "Yoga",
            originalName = "Keras Scaly"
        ),
    )
) : PreviewParameterProvider<Cast>

class CastsPreviewProvider(
    override val values: Sequence<List<Cast>> = sequenceOf(
        listOf(
            Cast(
                profilePath = "/cbEWkQM0FS9vzv07JFErCk0YKkx.jpg",
                name = "Keras",
                character = "Yoga",
                originalName = "Keras Scaly"
            ),
            Cast(
                profilePath = "/cbEWkQM0FS9vzv07JFErCk0YKkx.jpg",
                name = "Keras",
                character = "Yoga",
                originalName = "Keri Scaly"
            ),
        ),
    )
) : PreviewParameterProvider<List<Cast>>
