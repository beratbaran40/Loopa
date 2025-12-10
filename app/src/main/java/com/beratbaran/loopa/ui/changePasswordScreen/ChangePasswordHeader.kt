package com.beratbaran.loopa.ui.changePasswordScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.beratbaran.loopa.R

@Composable
fun ChangePasswordHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.change_password_screen_page_name),
            style = MaterialTheme.typography.displaySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        )
    }
}