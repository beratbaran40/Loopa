package com.beratbaran.loopa.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.MyappTheme

@Composable
fun DetailsHeader(
    imageUrl: String,
    name: String,
    mapButton: String,
    rating: String,
    navigateToMaps: () -> Unit,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onNavigateToBack: () -> Unit,

    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.details_screen_grid_img_4)
        )

        IconButton(
            onClick = { onNavigateToBack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.40f),
                    shape = CircleShape,
                ),
        ) {

            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp)
                    .clickable { onNavigateToBack() },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        IconButton(
            onClick = { onFavoriteClick() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.40f),
                    shape = CircleShape,
                ),
        ) {
            Icon(
                painter = painterResource(
                    id = if (isFavorite) R.drawable.ic_selected_favorite
                    else R.drawable.ic_favorite
                ),
                contentDescription = "Favorite",
                tint = MaterialTheme.colorScheme.primary,
            )
        }


        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            Color.Transparent,
                            if (isSystemInDarkTheme())
                                Color(0xE6000000).copy(alpha = 0.4f)
                            else Color(0xE6FFFFFF).copy(alpha = 0.4f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.width(8.dp))

                Icon(
                    painterResource(R.drawable.ic_star),
                    modifier = Modifier.size(20.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = rating,
                    style = MaterialTheme.typography.titleMedium
                        .copy(color = Color.White)
                )


                Spacer(Modifier.weight(1f))

                Button(
                    onClick = { navigateToMaps() },
                    modifier = Modifier.height(40.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(4.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_location),
                            modifier = Modifier.size(16.dp),
                            contentDescription = null,
                            tint = Color.Black,
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = mapButton,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.Black
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun DetailsHeaderPreview(
    imageUrl: String = "",
    name: String = "Taj Mahal",
    mapButton: String = "Show on Map",
    rating: String = "3.9",
    navigateToMaps: () -> Unit = {},
) {
    MyappTheme {
        DetailsHeader(
            imageUrl = imageUrl,
            name = name,
            mapButton = mapButton,
            navigateToMaps = navigateToMaps,
            rating = rating,
            isFavorite = false,
            onFavoriteClick = {},
            onNavigateToBack = {}
        )
    }
}