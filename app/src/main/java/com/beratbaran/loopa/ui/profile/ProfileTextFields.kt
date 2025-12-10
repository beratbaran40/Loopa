package com.beratbaran.loopa.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.LoopaTheme
import com.beratbaran.loopa.ui.theme.profileTextFieldColors

@Composable
fun ProfileTextFields(
    uiState: ProfileContract.UiState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ReadOnlyFields(
            value = uiState.name,
            label = stringResource(R.string.registerScreen_name_text),
            leadingIcon = R.drawable.ic_name,
        )

        ReadOnlyFields(
            value = uiState.surname,
            label = stringResource(R.string.registerScreen_surname_text),
            leadingIcon = R.drawable.ic_name,
        )

        ReadOnlyFields(
            value = uiState.email,
            label = stringResource(R.string.login_label_mail_text),
            leadingIcon = R.drawable.ic_email,
        )
        ReadOnlyFields(
            value = uiState.password,
            label = stringResource(R.string.login_label_password),
            leadingIcon = R.drawable.ic_password_custom
        )
    }
}

@Composable
private fun ReadOnlyFields(
    value: String,
    label: String,
    leadingIcon: Int,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        value = value,
        onValueChange = {},
        label = { Text(text = label) },
        singleLine = true,
        enabled = false,
        readOnly = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        shape = RoundedCornerShape(12.dp),
        colors = profileTextFieldColors(),
    )
}

@PreviewLightDark
@Composable
fun ProfileTextFieldsPreview() {
    LoopaTheme {
        ProfileTextFields(
            uiState = ProfileContract.UiState(),
        )
    }
}