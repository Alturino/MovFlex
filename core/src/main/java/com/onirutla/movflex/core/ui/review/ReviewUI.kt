package com.onirutla.movflex.core.ui.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.ui.ComponentPreview
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.core.R as coreR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewItemColumn(
    review: Review,
    modifier: Modifier = Modifier,
    onReviewClick: (Review) -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .wrapContentHeight()
            .padding(8.dp),
        onClick = { onReviewClick(review) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("$BASE_IMAGE_PATH${review.authorDetail.avatarPath}")
                        .build(),
                    contentDescription = "Reviewer Avatar",
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = review.author,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Justify,
                    )
                    Text(
                        text = review.createdAt,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Normal,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Justify,
                    )
                }
            }
            AssistChip(
                modifier = Modifier,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                },
                label = {
                    Text(text = "${review.authorDetail.rating}")
                },
                onClick = {}
            )
        }
        Text(
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            text = review.content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Normal,
            maxLines = 4,
            textAlign = TextAlign.Justify,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewItemRow(
    review: Review,
    modifier: Modifier = Modifier,
    onReviewClick: (Review) -> Unit = {},
    onChipClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .wrapContentHeight()
            .padding(8.dp),
        onClick = { onReviewClick(review) }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data("$BASE_IMAGE_PATH${review.authorDetail.avatarPath}")
                .build(),
            contentDescription = "Reviewer Avatar",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = review.authorDetail.username)
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = review.createdAt,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            AssistChip(
                modifier = Modifier,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                },
                label = {
                    Text(text = "${review.authorDetail.rating}")
                },
                onClick = onChipClick
            )
        }
        Text(
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            text = review.content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Normal,
            maxLines = 4,
            textAlign = TextAlign.Justify,
        )
    }
}

@Composable
fun ReviewColumn(modifier: Modifier = Modifier, reviews: List<Review>) {
    LazyColumn(modifier = modifier) {
        items(items = reviews) {
            ReviewItemColumn(review = it)
        }
    }
}

@Composable
fun ReviewRow(
    modifier: Modifier = Modifier,
    title: String,
    reviews: List<Review>,
    onReviewClick: (Review) -> Unit,
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
            )
        }
        LazyRow(
            modifier = modifier
                .wrapContentWidth()
                .wrapContentHeight(),
        ) {
            items(items = reviews) {
                ReviewItemRow(review = it, onReviewClick = onReviewClick)
            }
        }
    }
}

@ComponentPreview
@Composable
fun ReviewItemRowPreview(
    @PreviewParameter(ReviewProvider::class)
    review: Review,
) {
    MovFlexTheme {
        ReviewItemRow(review = review)
    }
}

@ComponentPreview
@Composable
fun ReviewItemColumnPreview(
    @PreviewParameter(ReviewProvider::class)
    review: Review,
) {
    MovFlexTheme {
        ReviewItemColumn(review = review)
    }
}

@ComponentPreview
@Composable
fun ReviewColumnPreview(
    @PreviewParameter(ReviewsProvider::class)
    reviews: List<Review>,
) {
    MovFlexTheme {
        ReviewColumn(reviews = reviews)
    }
}

@ComponentPreview
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ReviewRowPreview(
    @PreviewParameter(ReviewsProvider::class)
    reviews: List<Review>,
) {
    MovFlexTheme {
        ReviewRow(
            title = stringResource(id = coreR.string.reviews),
            reviews = reviews,
            onReviewClick = {}
        )
    }
}
