package com.beratbaran.loopa.ui.homepage

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiAction
import com.beratbaran.loopa.ui.theme.MyappTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun HomepageScreen(
    uiState: HomepageContract.UiState,
    uiEffect: Flow<HomepageContract.UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToDetails: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    places: List<PlaceModel>,
) {

    val userName = "User"
    val displayedPlaces = uiState.places.ifEmpty { places }

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            HomepageContract.UiEffect.NavigateToDetails -> onNavigateToDetails()
            HomepageContract.UiEffect.NavigateToFavorites -> onNavigateToFavorites()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
        ) {

            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp),
                painter = painterResource(id = R.drawable.loopa),
                contentDescription = null,
            )

            Text(
                modifier = Modifier.padding(top = 12.dp, start = 16.dp),
                text = stringResource(R.string.homepage_top_salute_message, userName),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp, bottom = 8.dp),
                text = stringResource(R.string.homepage_top_places),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                displayedPlaces.forEach { place ->
                    Box {
                        TopPlaceItem(
                            name = place.name,
                            location = place.location,
                            imageUrl = place.imageUrl,
                            rating = place.rating,
                            isFavorite = place.isFavorite,
                            onFavoriteClick = { onAction(UiAction.ToggleFavorite) },
                            onDetailsClick = { onAction(UiAction.OnDetailsClick) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp, bottom = 8.dp),
                text = stringResource(R.string.homepage_you_might_wanna_look),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))


            Column(
                Modifier
                    .fillMaxHeight()
            ) {
                displayedPlaces.forEach { place ->
                    Row(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        LookItem(
                            name = place.name,
                            location = place.location,
                            imageUrl = place.imageUrl,
                            rating = place.rating,
                            isFavorite = place.isFavorite,
                            onFavoriteClick = { onAction(UiAction.ToggleFavorite) },
                            onDetailsClick = { onAction(UiAction.OnDetailsClick) },
                        )
                    }
                }
            }
        }
    }
}

fun samplePlaces() = listOf(
    PlaceModel(
        name = "Bryce Canyon National Park",
        location = "Beyoğlu, İstanbul",
        imageUrl = "",
        rating = 4.7,
        isFavorite = false
    ),
    PlaceModel(
        name = "Maiden’s Tower",
        location = "Üsküdar, İstanbul",
        imageUrl = "",
        rating = 4.6,
        isFavorite = true
    ),
    PlaceModel(
        name = "Yerebatan Sarnıcı",
        location = "Fatih, İstanbul",
        imageUrl = "",
        rating = 4.8,
        isFavorite = false
    ),
    PlaceModel(
        name = "Grand Canyon",
        location = "Arizona, USA",
        imageUrl = "",
        rating = 4.9,
        isFavorite = false
    ),
    PlaceModel(
        name = "Mısır Çarşısı",
        location = "Fatih, İstanbul",
        imageUrl = "",
        rating = 3.6,
        isFavorite = true
    ),

    )

@Preview(showBackground = true)
@Composable
fun HomepageScreenPreview(
    @PreviewParameter(HomepageScreenPreviewProvider::class) uiState: HomepageContract.UiState,
) {
    MyappTheme {
        HomepageScreen(
            uiState = HomepageContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToDetails = {},
            places = samplePlaces(),
            onNavigateToFavorites = {},
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomepageScreenPreviewDark(@PreviewParameter(HomepageScreenPreviewProvider::class) uiState: HomepageContract.UiState) {
    MyappTheme {
        HomepageScreen(
            uiState = HomepageContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToDetails = {},
            places = samplePlaces(),
            onNavigateToFavorites = {},
        )
    }
}