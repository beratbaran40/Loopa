package com.beratbaran.loopa.ui.categories

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.MyappTheme

@Composable
fun CategoryItem(
    categories: String,
    @DrawableRes categoryIcon: Int,
    onCategoryClick: () -> Unit,
) {

    Card(
        modifier = Modifier
            .widthIn(max = 260.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
                shape = RoundedCornerShape(16.dp)),
        onClick = onCategoryClick,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {

            val isSDark = isSystemInDarkTheme()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                if (isSDark) Color.Black else Color.White,
                                Color.Transparent
                            )
                        )
                    ),
            ) {

                Icon(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(8.dp),
                    painter = painterResource(categoryIcon),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = null,
                )

            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp),
                text = categories,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CategoryItemPreview(
    categories: String = "Wildlife",
    @DrawableRes categoryIcon: Int = R.drawable.ic_wildlife,
) {
    MyappTheme {
        CategoryItem(
            categories = categories,
            categoryIcon = categoryIcon,
            onCategoryClick = {},
        )
    }
}