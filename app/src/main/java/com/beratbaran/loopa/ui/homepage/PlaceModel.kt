package com.beratbaran.loopa.ui.homepage

data class PlaceModel(
    val name: String,
    val location: String,
    val imageUrl: String?,
    val rating: Double,
    val isFavorite: Boolean,
    val id: Int,
    val description: String?,
    val categoryId: Int?,
)
