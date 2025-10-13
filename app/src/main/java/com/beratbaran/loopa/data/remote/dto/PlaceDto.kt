package com.beratbaran.loopa.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlaceDto(
    val id: Int?,
    val name: String?,
    val description: String?,
    val location: String?,
    val rating: Double?,
    val category: CategoryDto?,
    val coordinates: CoordinatesDto?,
    val favorites: List<FavoriteDto>?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("is_favorite") val isFavorite: Boolean?,
)

data class CategoryDto(
    val id: Int?,
    val name: String?,
    val icon: String?,
)

data class CoordinatesDto(
    @SerializedName("lat") val latitude: Double?,
    @SerializedName("lon") val longitude: Double?,
)

data class FavoriteDto(
    val id: Int?,
    val name: String?,
    val location: String?,
    val rating: Double?,
    val imageUrl: String?,
)