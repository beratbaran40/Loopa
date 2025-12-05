package com.beratbaran.loopa.ui.favorites

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.homepage.PlaceModel
import com.beratbaran.loopa.ui.theme.LoopaTheme

@Composable
fun FavoriteItem(
    place: PlaceModel,
    //item: FavoritesModel,
    onUnFavoriteClick: () -> Unit,
    onDetailsClick: () -> Unit,
) {
    Card(
        onClick = onDetailsClick,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.outlineVariant,
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box {
                AsyncImage(
                    modifier = Modifier.height(140.dp),
                    model = place.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.top_place_item_img),
                )

                IconButton(
                    onClick = onUnFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .size(32.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.40f),
                            shape = CircleShape,
                        ),
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.ic_selected_favorite),
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = place.name,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BottomInfo(
                    text = place.location,
                    icon = R.drawable.ic_location,
                )

                Spacer(modifier = Modifier.width(6.dp))

                BottomInfo(
                    text = place.rating.toString(),
                    icon = R.drawable.ic_star,
                    iconTint = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}

@Composable
private fun BottomInfo(
    text: String,
    @DrawableRes icon: Int,
    iconTint: Color = MaterialTheme.colorScheme.primary,
) {
    Icon(
        modifier = Modifier.size(14.dp),
        painter = painterResource(icon),
        contentDescription = null,
        tint = iconTint,
    )

    Spacer(modifier = Modifier.width(4.dp))

    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@PreviewLightDark
@Composable
fun FavoriteItemPreview(
    name: String = "Eiffel Tower",
    location: String = "Location",
    imageUrl: String = "Image",
    rating: Double = 4.5,
    isFavorite: Boolean = false,
) {
    LoopaTheme {
        FavoriteItem(
            place = PlaceModel(
                name = name,
                location = location,
                imageUrl = imageUrl,
                rating = rating,
                isFavorite = isFavorite,
                id = 0,
                description = "Description",
                categoryId = 1,
            ),
            onUnFavoriteClick = {},
            onDetailsClick = {},
        )
    }
}