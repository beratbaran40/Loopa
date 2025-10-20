package com.beratbaran.loopa.ui.categories

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.LoopaTheme

@Composable
fun CategoryItem(
    category: CategoryModel,
    onCategoryClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                ),
                shape = RoundedCornerShape(20.dp),
            ),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onCategoryClick,
    ) {
        val isSDark = isSystemInDarkTheme()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            if (isSDark) Color.Black else Color.White,
                            Color.Transparent,
                        )
                    )
                )
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier.size(120.dp),
                painter = painterResource(category.categoryIconRes),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = null,
            )

            Text(
                text = category.category,
                style = MaterialTheme.typography.bodyMedium,
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
    LoopaTheme {
        CategoryItem(
            category = CategoryModel(
                category = categories,
                categoryIconRes = categoryIcon,
            ),
            onCategoryClick = {},
        )
    }
}