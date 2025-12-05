package com.beratbaran.loopa.domain

data class PlaceDetailModel(
    val id: Int,
    val name: String,
    val description: String,
    val location: String,
    val rating: Double,
    val categoryId: Int,
    val categoryName: String,
    val latitude: Double?,
    val longitude: Double?,
    val images: List<String>,
    val isFavorite: Boolean,
)