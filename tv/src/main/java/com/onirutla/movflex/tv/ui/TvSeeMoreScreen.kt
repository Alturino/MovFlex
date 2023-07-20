package com.onirutla.movflex.tv.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.paging.compose.LazyPagingItems
import com.onirutla.movflex.tv.domain.model.Tv

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvSeeMoreScreen(
    modifier: Modifier = Modifier,
    title: String,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    onNavigateUp: () -> Unit,
    tvPaging: LazyPagingItems<Tv>,
    onTvClick: (Tv) -> Unit,
    onImageClick: (String) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { padding ->
        TvColumn(
            modifier = Modifier.padding(padding),
            tvPaging = tvPaging,
            onTvClick = onTvClick,
            onImageClick = onImageClick
        )
    }
}
