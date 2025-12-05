package com.beratbaran.loopa.data.model

import com.beratbaran.loopa.domain.PlaceDetailModel
import com.beratbaran.loopa.ui.homepage.PlaceModel
import com.google.gson.annotations.SerializedName

data class PlaceDto(
    val id: Int?,
    val name: String?,
    val description: String?,
    val location: String?,
    val rating: Double?,
    val category: CategoriesDto?,
    val coordinates: CoordinatesDto?,
    val favorites: List<FavoriteDto>?,
    @SerializedName("image") val image: String?,
    @SerializedName("images") val images: List<String>?,
    @SerializedName("is_favorite") val isFavorite: Boolean?,
)

data class HomePlacesDto(
    val topPlaces: List<PlaceDto>?,
    val wantToLookPlaces: List<PlaceDto>?,
)

data class PlaceResponseDto(
    val data: HomePlacesDto?,
    val message: String?,
    val isSuccess: Boolean,
)

data class PlaceDetailsResponseDto(
    val data: PlaceDto?,
    val message: String?,
    val isSuccess: Boolean,
)

data class CategoriesDto(
    val id: Int?,
    val name: String?,
)

data class CategoryResponseDto(
    val data: CategoriesDto?,
    val message: String?,
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
    @SerializedName("image") val image: String?,
)

fun PlaceDto.toDetailModel(): PlaceDetailModel {
    return PlaceDetailModel(
        id = id ?: 0,
        name = name.orEmpty(),
        description = description.orEmpty(),
        location = location.orEmpty(),
        rating = rating ?: 0.0,
        categoryId = category?.id ?: 0,
        categoryName = category?.name.orEmpty(),
        latitude = coordinates?.latitude,
        longitude = coordinates?.longitude,
        images = images ?: emptyList(),
        isFavorite = isFavorite ?: false
    )
}

fun PlaceDto.toModel(): PlaceModel {
    return PlaceModel(
        id = id ?: 0,
        name = name.orEmpty(),
        description = description.orEmpty(),
        location = location.orEmpty(),
        rating = rating ?: 0.0,
        imageUrl = image,
        isFavorite = isFavorite ?: false,
        categoryId = category?.id ?: 0,
    )
}