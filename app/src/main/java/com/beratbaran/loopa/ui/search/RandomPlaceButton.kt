package com.beratbaran.loopa.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.LoopaTheme

@Composable
fun RandomPlaceButton(
    onClick: () -> Unit,
) {

    Box {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(32.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                ),
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(
                    id = R.drawable.ic_random
                ),
                contentDescription = "Favorite",
                tint = MaterialTheme.colorScheme.background,
            )
        }
    }


}

@PreviewLightDark
@Composable
fun RandomPlaceButtonPreview(
    onClick: () -> Unit = {},
) {
    LoopaTheme {
        RandomPlaceButton(
            onClick = onClick,
        )
    }
}