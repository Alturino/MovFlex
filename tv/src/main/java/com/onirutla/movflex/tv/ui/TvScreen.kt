package com.onirutla.movflex.tv.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvScreen(
    modifier: Modifier = Modifier,
    firstRowTv: List<Tv>,
    firstRowTitle: String,
    secondRowTv: List<Tv> = emptyList(),
    secondRowTitle: String = "",
    thirdRowTv: List<Tv> = emptyList(),
    thirdRowTitle: String = "",
    fourthRowTv: List<Tv> = emptyList(),
    fourthRowTitle: String = "",
    verticalScrollState: ScrollState = rememberScrollState(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {},
        bottomBar = {},
        snackbarHost = {},
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.End,
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(verticalScrollState)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(padding)
        ) {
            TvRow(
                tvList = firstRowTv,
                tvRowTitle = firstRowTitle,
                onItemClick = {},
                onImageClick = {},
                onSeeMoreClick = {}
            )
            TvRow(
                tvList = secondRowTv,
                tvRowTitle = secondRowTitle,
                onItemClick = {},
                onImageClick = {},
                onSeeMoreClick = {}
            )
            TvRow(
                tvList = thirdRowTv,
                tvRowTitle = thirdRowTitle,
                onItemClick = {},
                onImageClick = {},
                onSeeMoreClick = {}
            )
            TvRow(
                tvList = fourthRowTv,
                tvRowTitle = fourthRowTitle,
                onItemClick = {},
                onImageClick = {},
                onSeeMoreClick = {}
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TvScreenPreview(
    @PreviewParameter(TvParameterProvider::class)
    tvList: List<Tv>
) {
    MovFlexTheme {
        TvScreen(
            firstRowTv = tvList,
            firstRowTitle = stringResource(id = TvType.TV_TOP_RATED.value),
            secondRowTv = tvList,
            secondRowTitle = stringResource(id = TvType.TV_POPULAR.value),
            thirdRowTv = tvList,
            thirdRowTitle = stringResource(id = TvType.TV_AIRING_TODAY.value),
            fourthRowTv = tvList,
            fourthRowTitle = stringResource(id = TvType.TV_ON_THE_AIR.value),
        )
    }
}
