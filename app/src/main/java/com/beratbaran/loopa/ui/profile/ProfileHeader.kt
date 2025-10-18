package com.beratbaran.loopa.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.profile.ProfileContract.UiAction
import com.beratbaran.loopa.ui.theme.MyappTheme

@Composable
fun ProfileHeader(
    onAction: (UiAction) -> Unit,
    onNavigateToBack: () -> Unit,
    isInEditMode: Boolean,
    areFieldsEmpty: Boolean,
) {

    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {

        Icon(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 18.dp, top = 18.dp)
                .size(24.dp)
                .clickable { onNavigateToBack() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
        )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.profile_screen_page_name),
            style = MaterialTheme.typography.displaySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        )

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 12.dp, top = 8.dp)
        ) {

            if (isInEditMode) {
                IconButton(
                    onClick = { onAction(UiAction.OnConfirmChangesClick) },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.primary
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_done_changes),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
            }

            IconButton(
                onClick = {
                    if (!isInEditMode) {
                        onAction(UiAction.OnEditProfileClick)
                    } else {
                        onAction(UiAction.OnCancelChangesClick)
                    }
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        color = when {
                            isInEditMode && !areFieldsEmpty -> MaterialTheme.colorScheme.error
                            isInEditMode && areFieldsEmpty -> MaterialTheme.colorScheme.error
                            else -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1f)
                        }
                    )
            ) {

                Icon(
                    painter = painterResource(
                        id = when {
                            !isInEditMode -> R.drawable.ic_edit_profile
                            else -> R.drawable.ic_cancel
                        }
                    ),
                    contentDescription = null,
                    tint = when {
                        isInEditMode -> MaterialTheme.colorScheme.background
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ProfileHeaderPreview() {
    MyappTheme {
        ProfileHeader(
            onAction = {},
            onNavigateToBack = {},
            isInEditMode = true,
            areFieldsEmpty = false,
        )
    }
}