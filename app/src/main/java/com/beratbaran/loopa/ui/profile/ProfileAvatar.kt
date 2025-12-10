package com.beratbaran.loopa.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.profile.ProfileContract.UiState
import com.beratbaran.loopa.ui.theme.LoopaTheme

@Composable
fun ProfileAvatar(
    uiState: UiState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .height(180.dp)
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.loopa),
            contentDescription = null,
        )

        Text(
            text = uiState.fullName,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@PreviewLightDark
@Composable
fun ProfileAvatarPreview(
    imageUrl: String = "Avatar",
) {
    LoopaTheme {
        ProfileAvatar(
            uiState = UiState()
        )
    }
}