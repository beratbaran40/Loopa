package com.beratbaran.loopa.ui.categorydetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.MyappTheme

@Composable
fun DetailItem(
    name: String,
    location: String,
    imageUrl: String,
    rating: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onDetailsClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .widthIn(max = 500.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                shape = RoundedCornerShape(16.dp)
            ),
        onClick = onDetailsClick,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {

            val isDark = isSystemInDarkTheme()

            Box(
                modifier = Modifier
                    .height(180.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                if (isDark) Color.Black else Color.White,
                                Color.Transparent
                            )
                        )
                    ),
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.top_place_item_img),
                )

                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.40f),
                            shape = CircleShape,
                        ),
                ) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(
                            id = if (isFavorite) R.drawable.ic_selected_favorite
                            else R.drawable.ic_favorite
                        ),
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(R.drawable.ic_location),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = location,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Spacer(modifier = Modifier.width(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painterResource(R.drawable.ic_star),
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = rating,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun TopPlaceItemPreview(
    name: String = "Bryce Canyon National Park",
    location: String = "Location",
    imageUrl: String = "Image",
    rating: String = "4.5",
    isFavorite: Boolean = false,
) {
    MyappTheme {
        DetailItem(
            name = name,
            location = location,
            imageUrl = imageUrl,
            rating = rating,
            isFavorite = isFavorite,
            onFavoriteClick = {},
            onDetailsClick = {},
        )
    }
}