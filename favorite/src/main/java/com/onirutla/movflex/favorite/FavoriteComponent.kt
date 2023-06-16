package com.onirutla.movflex.favorite

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.ui.MovFlexTheme

@Composable
fun FavoriteTab(
    modifier: Modifier = Modifier,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    title: String = "Movie",
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = { Text(text = title) },
        icon = icon,
        interactionSource = interactionSource,
    )
}

@Composable
fun FavoriteTabRow(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabClick: (selectedIndex: Int) -> Unit,
) {
    TabRow(modifier = modifier, selectedTabIndex = selectedTab) {
        FavoriteTab(
            selected = selectedTab == 0,
            onClick = { onTabClick(0) },
            title = stringResource(id = R.string.movie),
            icon = { Icon(imageVector = Icons.Default.Movie, contentDescription = null) }
        )
        FavoriteTab(
            selected = selectedTab == 1,
            onClick = { onTabClick(1) },
            title = stringResource(id = R.string.tv),
            icon = { Icon(imageVector = Icons.Default.Tv, contentDescription = null) }
        )
    }
}

@Preview
@Composable
fun FavoriteTabPreview() {
    MovFlexTheme {
        FavoriteTab(
            selected = false,
            onClick = {},
            title = "Movie",
            icon = {
                Icon(imageVector = Icons.Default.Movie, contentDescription = null)
            }
        )
    }
}

@Preview
@Composable
fun FavoriteTabRowPreview(
    selectedTabIndex: Int = 0,
) {
    MovFlexTheme {
        FavoriteTabRow(selectedTab = selectedTabIndex, onTabClick = {})
    }
}
