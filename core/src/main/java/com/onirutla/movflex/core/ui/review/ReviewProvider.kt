package com.onirutla.movflex.core.ui.review

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.onirutla.movflex.core.domain.model.AuthorDetail
import com.onirutla.movflex.core.domain.model.Review

private val dummyReviews = listOf(
    Review(
        author = "onirutlA",
        authorDetail = AuthorDetail(
            avatarPath = "",
            name = "Ricky Alturino",
            rating = 0,
            username = "onirutlA",
        ),
        content = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sodales
            laoreet commodo. Phasellus a purus eu risus elementum consequat. Aenean eu
            elit ut nunc convallis laoreet non ut libero. Suspendisse interdum placerat
            risus vel ornare. Donec vehicula, turpis sed consectetur ullamcorper, ante
            nunc egestas quam, ultricies adipiscing velit enim at nunc. Aenean id diam
            neque. Praesent ut lacus sed justo viverra fermentum et ut sem. Fusce
            convallis gravida lacinia. Integer semper dolor ut elit sagittis lacinia.
            Praesent sodales scelerisque eros at rhoncus. Duis posuere sapien vel ipsum
            ornare interdum at eu quam. Vestibulum vel massa erat. Aenean quis sagittis
            purus. Phasellus arcu purus, rutrum id consectetur non, bibendum at nibh.
        """.trimIndent(),
        createdAt = "27-03-23",
        id = "",
        updatedAt = "",
        url = ""
    )
)

class ReviewProvider(
    override val values: Sequence<Review> = sequenceOf(dummyReviews.first())
) : PreviewParameterProvider<Review>

class ReviewsProvider(
    override val values: Sequence<List<Review>> = sequenceOf(dummyReviews)
) : PreviewParameterProvider<List<Review>>
