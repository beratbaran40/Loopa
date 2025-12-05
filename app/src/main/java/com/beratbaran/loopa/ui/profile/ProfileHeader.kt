package com.beratbaran.loopa.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.LoopaTheme

@Composable
fun ProfileHeader(
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .statusBarsPadding(),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.profile_screen_page_name),
            style = MaterialTheme.typography.displaySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        )
    }
}

@PreviewLightDark
@Composable
fun ProfileHeaderPreview() {
    LoopaTheme {
        ProfileHeader(
        )
    }
}