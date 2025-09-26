package com.beratbaran.loopa.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.ui.theme.MyappTheme

@Composable
fun DetailsCard(
    category: String,
    categoryName: String,
    location: String,
    locationName: String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .border(
                    1.dp, MaterialTheme.colorScheme.onSurfaceVariant,
                    RoundedCornerShape(6.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(12.dp)
        )
        {
            Column {
                Text(
                    text = category,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = categoryName,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .border(
                    1.dp, MaterialTheme.colorScheme.onSurfaceVariant,
                    RoundedCornerShape(6.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = location,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = locationName,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun DetailsCardPreview() {
    MyappTheme {
        DetailsCard(
            category = "Category",
            categoryName = "Category Name",
            location = "Location",
            locationName = "Location Name"
        )
    }
}