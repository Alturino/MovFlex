package com.onirutla.movflex.tv.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.ui.cast.CastRow
import com.onirutla.movflex.core.ui.review.ReviewRow
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvDetail
import com.onirutla.movflex.core.R as coreR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvDetailScreen(
    modifier: Modifier = Modifier,
    tvDetail: TvDetail,
    reviews: List<Review>,
    onReviewClick: (Review) -> Unit,
    casts: List<Cast>,
    onCastClick: (Cast) -> Unit,
    fabState: Boolean = false,
    onFabClick: (TvDetail) -> Unit,
    tvRecommendations: List<Tv>,
    tvSimilar: List<Tv>,
    onTvClick: (Tv) -> Unit,
    onTvSeeMoreClick: (String) -> Unit,
    onImageClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
    verticalScrollState: ScrollState = rememberScrollState(),
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = { onFabClick(tvDetail) }) {
                Icon(
                    imageVector = if (fabState) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                    tint = Color.Red,
                    contentDescription = "Favorite Button"
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(contentPadding)
                .verticalScroll(verticalScrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("$BASE_IMAGE_PATH${tvDetail.posterPath}")
                        .build(),
                    contentDescription = "Movie Poster",
                    placeholder = painterResource(id = coreR.drawable.ic_launcher_background),
                    contentScale = ContentScale.Crop,
                )
                IconButton(
                    modifier = Modifier.align(Alignment.TopStart),
                    onClick = { onNavigateUp() }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button")
                }
            }
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = tvDetail.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(text = tvDetail.originalName)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Icon",
                        tint = Color.Yellow
                    )
                    Text(text = "${tvDetail.voteAverage / 2}")
                    Text(text = "(${tvDetail.voteCount})")
                }
                Text(
                    text = tvDetail.genres,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = stringResource(id = coreR.string.overview),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                )
                Text(
                    text = tvDetail.overview,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
                ReviewRow(
                    reviews = reviews,
                    onReviewClick = onReviewClick,
                )
                CastRow(
                    title = stringResource(id = coreR.string.casts),
                    casts = casts,
                    onCastClick = onCastClick,
                    seeMore = stringResource(id = coreR.string.see_more)
                )
                TvRow(
                    title = stringResource(id = coreR.string.recommendation),
                    tvList = tvRecommendations,
                    onTvClick = onTvClick,
                    onSeeMoreClick = onTvSeeMoreClick,
                    onImageClick = onImageClick,
                )
                TvRow(
                    title = stringResource(id = coreR.string.similar),
                    tvList = tvSimilar,
                    onTvClick = onTvClick,
                    onSeeMoreClick = onTvSeeMoreClick,
                    onImageClick = onImageClick,
                )
            }
        }
    }
}
