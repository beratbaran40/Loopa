package com.beratbaran.loopa.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.MyappTheme

@Composable
fun DetailsGrid() {

    val images = listOf(
        R.drawable.details_screen_grid_img_1,
        R.drawable.details_screen_grid_img_2,
        R.drawable.details_screen_grid_img_3,
        R.drawable.details_screen_grid_img_4
    )

    var selectedIndex by remember { mutableStateOf<Int?>(null) }

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
                painter = painterResource(R.drawable.details_screen_grid_img_1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(0.7f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { selectedIndex = 0 }
            )

            Image(
                painter = painterResource(R.drawable.details_screen_grid_img_2),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { selectedIndex = 1 }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.details_screen_grid_img_3),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { selectedIndex = 2 }
            )

            Image(
                painter = painterResource(R.drawable.details_screen_grid_img_4),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { selectedIndex = 3 }
            )
        }

        if (selectedIndex != null) {
            Dialog(
                onDismissRequest = { selectedIndex = null },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false,
                    dismissOnClickOutside = true
                )
            ) {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }) {
                                selectedIndex = null
                            }
                            .padding(24.dp)
                    ) {
                        var totalDragX by remember { mutableFloatStateOf(0f) }

                        Image(
                            painter = painterResource(images[selectedIndex!!]),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxWidth()
                                .shadow(
                                    8.dp,
                                    RoundedCornerShape(20.dp)
                                )
                                .border(
                                    2.dp,
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                    RoundedCornerShape(20.dp)
                                )
                                .clip(
                                    RoundedCornerShape(20.dp)
                                )
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {}
                                .pointerInput(selectedIndex) {
                                    detectDragGestures(
                                        onDragEnd = {
                                            val threshold = 100f
                                            selectedIndex?.let { idx ->
                                                if (totalDragX < -threshold) {
                                                    selectedIndex = (idx + 1) % images.size
                                                } else if (totalDragX > threshold) {
                                                    selectedIndex = if (idx - 1 >= 0) idx - 1 else
                                                        images.lastIndex
                                                }
                                            }
                                            totalDragX = 0f
                                        },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            totalDragX += dragAmount.x
                                        }
                                    )
                                }
                        )

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
                                        .size(12.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (index == selectedIndex) MaterialTheme.colorScheme.primary
                                            else MaterialTheme.colorScheme.background.copy(alpha = 1f)
                                        )
                                )
                            }
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