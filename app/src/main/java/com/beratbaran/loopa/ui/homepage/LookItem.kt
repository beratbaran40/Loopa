package com.beratbaran.loopa.ui.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
fun LookItem(
    name: String,
    location: String,
    imageUrl: String,
    rating: Double,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onDetailsClick: () -> Unit
) {

    Card(
        onClick = onDetailsClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(4.dp),
                ) {

                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(120.dp),
                    placeholder = painterResource(R.drawable.look_item_img)
                )
            }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Top
                        ) {

                            Text(
                                text = name,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .padding(end = 6.dp)
                                    .size(42.dp)
                                    .clip(
                                        CircleShape
                                    )
                                    .background(
                                        MaterialTheme.colorScheme.primaryContainer.copy(
                                            alpha = 0.40f
                                        )
                                    ),
                            ) {
                                IconButton(onClick = onFavoriteClick) {
                                    Icon(
                                        painter = painterResource(
                                            id = if (isFavorite) R.drawable.ic_selected_favorite
                                            else R.drawable.ic_favorite
                                        ),
                                        contentDescription = "Favorite",
                                        modifier = Modifier.size(28.dp),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }

                        }

                        Spacer(
                            modifier = Modifier
                                .height(12.dp)
                        )

                        Column {

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painterResource(R.drawable.ic_location),
                                    tint = MaterialTheme.colorScheme.primary,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(
                                    modifier = Modifier
                                        .width(4.dp)
                                )

                                Text(
                                    text = location,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painterResource(R.drawable.ic_star),
                                tint = MaterialTheme.colorScheme.secondary,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "%.1f".format(rating),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
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
fun LookItemPreview(
    name: String = "Look Item",
    location: String = "Location",
    imageUrl: String = "Image",
    rating: Double = 4.1,
    isFavorite: Boolean = true,
) {
    MyappTheme {
        LookItem(
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

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LookItemPreviewDark(
    name: String = "Look Item",
    location: String = "Location",
    imageUrl: String = "Image",
    rating: Double = 4.1,
    isFavorite: Boolean = true,
) {
    MyappTheme {
        LookItem(
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