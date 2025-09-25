package com.beratbaran.loopa.ui.homepage

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.MyappTheme

@Composable
fun TopPlaceItem(
    name: String,
    location: String,
    imageUrl: String,
    rating: Double,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onDetailsClick: () -> Unit,
) {
    Card(
        onClick = onDetailsClick,
        elevation = CardDefaults
            .cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(12.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer
                ),

            ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .aspectRatio(1.1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize(),
                            placeholder = painterResource(R.drawable.top_place_item_img)
                        )

                        IconButton(
                            onClick = onFavoriteClick,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.40f),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = if (isFavorite) R.drawable.ic_selected_favorite
                                    else R.drawable.ic_favorite
                                ),
                                contentDescription = "Favorite",
                                modifier = Modifier.size(36.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_location),
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = location,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painterResource(R.drawable.ic_star),
                            tint = MaterialTheme.colorScheme.secondary,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "%.1f".format(rating),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopPlaceItemPreview(
    name: String = "Bryce Canyon National Park",
    location: String = "Location",
    imageUrl: String = "Image",
    rating: Double = 4.5,
    isFavorite: Boolean = false,
) {
    MyappTheme {
        TopPlaceItem(
            name = name,
            location = location,
            imageUrl = imageUrl,
            rating = rating,
            isFavorite = isFavorite,
            onFavoriteClick = {},
            onDetailsClick = {}
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TopPlacePreviewDark(
    name: String = "Bryce Canyon National Park",
    location: String = "Location",
    imageUrl: String = "Image",
    rating: Double = 4.5,
    isFavorite: Boolean = false,
) {
    MyappTheme {
        TopPlaceItem(
            name = name,
            location = location,
            imageUrl = imageUrl,
            rating = rating,
            isFavorite = isFavorite,
            onFavoriteClick = {},
            onDetailsClick = {}
        )
    }
}