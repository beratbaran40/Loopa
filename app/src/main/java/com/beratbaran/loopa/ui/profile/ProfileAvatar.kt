package com.beratbaran.loopa.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { }
        ) {
            Image(
                painter = painterResource(R.drawable.ic_avatar),
                contentDescription = "Profile Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = uiState.fullName,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@PreviewLightDark
@Composable
fun ProfileAvatarPreview(
    imageUrl: String = "Profile Avatar",
) {
    LoopaTheme {
        ProfileAvatar(
            uiState = UiState()
        )
    }
}