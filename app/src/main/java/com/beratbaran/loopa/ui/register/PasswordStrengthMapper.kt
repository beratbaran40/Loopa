package com.beratbaran.loopa.ui.register

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.PasswordStrength

@Composable
fun PasswordStrength.toProgress(): Triple<Float, Color, String> {
    return when (this) {
        PasswordStrength.STRONG -> Triple(
            1f,
            MaterialTheme.colorScheme.primary,
            stringResource(R.string.register_password_strength_STRONG),
        )

        PasswordStrength.MEDIUM -> Triple(
            0.50f,
            MaterialTheme.colorScheme.secondary,
            stringResource(R.string.register_password_strength_MEDIUM),
        )

        PasswordStrength.WEAK -> Triple(
            0.25f,
            MaterialTheme.colorScheme.error,
            stringResource(R.string.register_password_strength_WEAK),
        )
    }
}