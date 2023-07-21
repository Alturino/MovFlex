package com.onirutla.movflex.movie.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onirutla.movflex.core.ui.ComponentPreview
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.movie.R
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieParameterProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItemColumn(
    modifier: Modifier = Modifier,
    movie: Movie,
    onImageClick: (url: String) -> Unit,
    onItemClick: (movie: Movie) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        onClick = { onItemClick(movie) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(125.dp)
                    .height(225.dp)
                    .clickable { onImageClick("$BASE_IMAGE_PATH${movie.posterPath}") }
                    .clip(RoundedCornerShape(8.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_IMAGE_PATH${movie.posterPath}")
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    textAlign = TextAlign.Left,
                )
                Text(
                    text = movie.originalTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    textAlign = TextAlign.Left,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Text(
                        text = "${movie.voteAverage / 2}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        maxLines = 4,
                        textAlign = TextAlign.Left,
                    )
                }
                Text(
                    text = "Movie",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 4,
                    textAlign = TextAlign.Left,
                )
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 4,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = movie.genres,
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
private fun MovieItemColumnPreview(
    @PreviewParameter(provider = MovieParameterProvider::class) movie: Movie,
) {
    MovFlexTheme {
        MovieItemColumn(movie = movie, onItemClick = {}, onImageClick = {})
    }
}
