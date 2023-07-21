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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
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

data class FavoriteTabItem(
    val title: String,
    val icon: ImageVector,
)

@Composable
fun FavoriteTabRow(
    modifier: Modifier = Modifier,
    tabItems: List<FavoriteTabItem>,
    selectedTab: Int,
    onTabClick: (selectedTab: Int) -> Unit,
) {
    TabRow(modifier = modifier, selectedTabIndex = selectedTab) {
        tabItems.forEachIndexed { index, item ->
            FavoriteTab(
                selected = selectedTab == index,
                onClick = { onTabClick(index) },
                title = item.title,
                icon = { Icon(imageVector = item.icon, contentDescription = null) }
            )
        }
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
        FavoriteTabRow(
            selectedTab = selectedTabIndex,
            tabItems = listOf(
                FavoriteTabItem("Movie", icon = Icons.Default.Movie),
                FavoriteTabItem("Tv", icon = Icons.Default.Tv),
            ),
            onTabClick = {}
        )
    }
}
