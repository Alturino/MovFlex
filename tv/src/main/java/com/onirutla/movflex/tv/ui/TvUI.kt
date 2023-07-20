package com.onirutla.movflex.tv.ui

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onirutla.movflex.core.ui.ComponentPreview
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.tv.R
import com.onirutla.movflex.tv.domain.model.Tv

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvItemRow(
    tv: Tv,
    modifier: Modifier = Modifier,
    onImageClick: (url: String) -> Unit = {},
    onItemClick: (tv: Tv) -> Unit = {},
) {
    Card(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min)
            .padding(8.dp),
        onClick = { onItemClick(tv) },
    ) {
        AsyncImage(
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .clickable { onImageClick("$BASE_IMAGE_PATH${tv.posterPath}") },
            model = ImageRequest.Builder(LocalContext.current)
                .data("$BASE_IMAGE_PATH${tv.posterPath}")
                .build(),
            contentDescription = "Reviewer Avatar",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .width(250.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = tv.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
            )
            Text(
                text = tv.originalName,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
            )
            Text(
                text = tv.overview,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )

            Text(
                text = tv.firstAirDate,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
            Text(
                text = tv.genres,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )
                Text(
                    text = "${tv.voteAverage}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                )
                Text(text = "(${tv.voteCount})")
            }
        }
    }
}

@Composable
@ComponentPreview
private fun TvItemRowPreview(
    @PreviewParameter(TvParameterProvider::class) tv: Tv,
) {
    MovFlexTheme {
        TvItemRow(tv = tv)
    }
}

@ComponentPreview
@Composable
private fun TvRowPreview(
    @PreviewParameter(TvListParameterProvider::class) tvList: List<Tv>,
) {
    MovFlexTheme {
        TvRow(tvList = tvList, onTvClick = {}, onSeeMoreClick = {}, onImageClick = {})
    }
}

@Composable
fun TvRow(
    modifier: Modifier = Modifier,
    tvList: List<Tv>,
    title: String = "Popular",
    seeMore: String = "See More",
    onSeeMoreClick: (String) -> Unit,
    onImageClick: (url: String) -> Unit,
    onTvClick: (tv: Tv) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
            )
            Text(
                modifier = Modifier.clickable { onSeeMoreClick(title) },
                text = seeMore,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Left,
            )
        }
        LazyRow {
            items(items = tvList) {
                TvItemRow(tv = it, onImageClick = onImageClick, onItemClick = onTvClick)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvItemColumn(
    tv: Tv,
    modifier: Modifier = Modifier,
    onImageClick: (url: String) -> Unit = {},
    onItemClick: (tv: Tv) -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        onClick = { onItemClick(tv) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(125.dp)
                    .height(225.dp)
                    .clickable { onImageClick("$BASE_IMAGE_PATH${tv.posterPath}") }
                    .clip(RoundedCornerShape(8.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_IMAGE_PATH${tv.posterPath}")
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = tv.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    maxLines = 4,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = tv.originalName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 4,
                    textAlign = TextAlign.Justify,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Text(
                        text = "${tv.voteAverage / 2}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        maxLines = 4,
                        textAlign = TextAlign.Justify,
                    )
                }
                Text(
                    text = "Tv",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 4,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = tv.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 4,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = tv.genres,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 4,
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}

@ComponentPreview
@Composable
private fun TvItemColumnPreview(
    @PreviewParameter(TvParameterProvider::class) tv: Tv,
) {
    MovFlexTheme {
        TvItemColumn(tv = tv)
    }
}

@Composable
fun TvColumn(
    tvList: List<Tv>,
    modifier: Modifier = Modifier,
    onImageClick: (url: String) -> Unit = {},
    onItemClick: (tv: Tv) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(items = tvList) {
            TvItemColumn(tv = it, onImageClick = onImageClick, onItemClick = onItemClick)
        }
    }
}


@Composable
fun TvColumn(
    tvPaging: LazyPagingItems<Tv>,
    modifier: Modifier = Modifier,
    onImageClick: (url: String) -> Unit = {},
    onTvClick: (tv: Tv) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(items = tvPaging) {
            if (it != null) {
                TvItemColumn(
                    tv = it,
                    onImageClick = onImageClick,
                    onItemClick = onTvClick
                )
            }
        }
    }
}

@ComponentPreview
@Composable
private fun TvColumnPreview(
    @PreviewParameter(TvListParameterProvider::class) tvList: List<Tv>,
) {
    MovFlexTheme {
        TvColumn(tvList = tvList)
    }
}
