package com.beratbaran.loopa.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.MyappTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ENTER_MS = 250
private const val EXIT_MS = 200
private const val EXIT_DELAY_MS = EXIT_MS + 20
private const val INDICATOR_SCROLLED_MS = 300

@Composable
fun DetailsGrid() {

    val images = listOf(
        R.drawable.details_screen_grid_img_1,
        R.drawable.details_screen_grid_img_2,
        R.drawable.details_screen_grid_img_3,
        R.drawable.details_screen_grid_img_4,
    )

    var selectedIndex by rememberSaveable { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Image(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        selectedIndex = 0
                    },
                painter = painterResource(R.drawable.details_screen_grid_img_1),
                contentDescription = stringResource(R.string.details_screen_grid_images_description_1_4),
                contentScale = ContentScale.Crop,
            )

            Image(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        selectedIndex = 1
                    },
                painter = painterResource(R.drawable.details_screen_grid_img_2),
                contentDescription = stringResource(R.string.details_screen_grid_images_description_2_4),
                contentScale = ContentScale.Crop,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        selectedIndex = 2
                    },
                painter = painterResource(R.drawable.details_screen_grid_img_3),
                contentDescription = stringResource(R.string.details_screen_grid_images_description_3_4),
                contentScale = ContentScale.Crop,
            )

            Image(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        selectedIndex = 3
                    },
                painter = painterResource(R.drawable.details_screen_grid_img_4),
                contentDescription = stringResource(R.string.details_screen_grid_images_description_4_4),
                contentScale = ContentScale.Crop,
            )
        }

        if (selectedIndex != null) {
            GalleryDialog(
                images = images,
                initialIndex = selectedIndex!!,
                onClose = { selectedIndex = null }
            )
        }
    }
}

@Composable
private fun GalleryDialog(
    images: List<Int>,
    initialIndex: Int,
    onClose: (finalIndex: Int) -> Unit,
) {
    var isDialogContentVisible by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val noIndication = remember { MutableInteractionSource() }

    val pagerState = rememberPagerState(
        initialPage = initialIndex,
        pageCount = { images.size })

    fun close() {
        isDialogContentVisible = false
        scope.launch {
            delay(EXIT_DELAY_MS.toLong())
            onClose(pagerState.currentPage)
        }
    }

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {

        AnimatedVisibility(
            visible = isDialogContentVisible,
            enter = fadeIn(animationSpec = tween(ENTER_MS)) + scaleIn(
                animationSpec = tween(ENTER_MS),
                initialScale = 0.92f
            ),
            exit = fadeOut(animationSpec = tween(EXIT_MS)) + scaleOut(
                animationSpec = tween(EXIT_MS),
                targetScale = 0.92f
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = noIndication
                    ) {
                        close()
                    }
                    .padding(24.dp)
            ) {

                HorizontalPager(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    state = pagerState,
                    pageSpacing = 12.dp
                )
                { page ->
                    Image(
                        modifier = Modifier
                            .shadow(8.dp, RoundedCornerShape(20.dp))
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                RoundedCornerShape(20.dp)
                            )
                            .clip(RoundedCornerShape(20.dp))
                            .clickable(
                                indication = null,
                                interactionSource = noIndication
                            ) { },
                        painter = painterResource(images[page]),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    images.forEachIndexed { index, _ ->

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(24.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = noIndication
                                ) {
                                    scope.launch {
                                        pagerState.animateScrollToPage(
                                            index, animationSpec = tween(
                                                INDICATOR_SCROLLED_MS
                                            )
                                        )
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (index == pagerState.currentPage)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsGridPreview() {
    MyappTheme {
        DetailsGrid()
    }
}