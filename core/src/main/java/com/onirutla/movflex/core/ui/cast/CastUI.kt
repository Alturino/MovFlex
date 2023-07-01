package com.onirutla.movflex.core.ui.cast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.ui.CastProvider
import com.onirutla.movflex.core.ui.CastsProvider
import com.onirutla.movflex.core.ui.ComponentPreview
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CastItemColumn(
    modifier: Modifier = Modifier,
    cast: Cast,
    onClick: (Cast) -> Unit = {}
) {
    Card(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp),
        onClick = { onClick(cast) }
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(125.dp)
                    .height(125.dp)
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_IMAGE_PATH${cast.profilePath}")
                    .build(),
                contentDescription = "Cast Image",
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp, bottom = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = cast.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = "(${cast.character})",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CastItemRow(
    modifier: Modifier = Modifier,
    cast: Cast,
    onClick: (Cast) -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(250.dp)
            .wrapContentHeight()
            .padding(8.dp),
        onClick = { onClick(cast) }
    ) {
        AsyncImage(
            modifier = Modifier
                .width(250.dp)
                .height(325.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data("$BASE_IMAGE_PATH${cast.profilePath}")
                .build(),
            contentDescription = "Cast Image",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = cast.originalName,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
            Text(
                text = "(${cast.character})",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
        }
    }
}


@Composable
fun CastRow(
    modifier: Modifier = Modifier,
    title: String,
    casts: List<Cast>,
    onCastClick: (Cast) -> Unit,
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
            items(items = casts) {
                CastItemRow(cast = it, onClick = onCastClick)
            }
        }
    }
}

@Composable
fun CastColumn(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    casts: List<Cast>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = contentPadding
    ) {
        items(items = casts) {
            CastItemColumn(cast = it)
        }
    }
}

@ComponentPreview
@Composable
fun CastItemRowPreview(
    @PreviewParameter(CastProvider::class)
    cast: Cast
) {
    MovFlexTheme {
        CastItemRow(cast = cast)
    }
}

@ComponentPreview
@Composable
fun CastItemColumnPreview(
    @PreviewParameter(CastProvider::class)
    cast: Cast
) {
    MovFlexTheme {
        CastItemColumn(cast = cast)
    }
}

@ComponentPreview
@Composable
fun CastColumnPreview(
    @PreviewParameter(CastsProvider::class)
    casts: List<Cast>
) {
    MovFlexTheme {
        CastColumn(casts = casts)
    }
}

@ComponentPreview
@Composable
fun CastRowPreview(
    @PreviewParameter(CastsProvider::class)
    casts: List<Cast>
) {
    MovFlexTheme {
        CastRow(title = "Cast", casts = casts, onCastClick = {})
    }
}
