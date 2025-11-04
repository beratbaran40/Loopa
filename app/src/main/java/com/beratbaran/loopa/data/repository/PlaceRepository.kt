package com.beratbaran.loopa.data.repository

import com.beratbaran.loopa.data.model.CategoryDto
import com.beratbaran.loopa.data.model.FavoriteDto
import com.beratbaran.loopa.data.model.PlaceDto
import com.beratbaran.loopa.data.model.UserDto
import com.beratbaran.loopa.data.remote.api.LoopaApi
import javax.inject.Inject

class PlaceRepository @Inject constructor(
    private val api: LoopaApi,
) {
    suspend fun getPlaces(): List<PlaceDto> = api.getPlaces()

    suspend fun getCategories(): List<CategoryDto> = api.getCategories()

    suspend fun getFavorites(): List<FavoriteDto> = api.getFavorites()

    suspend fun updatePassword(password: UserDto): UserDto = api.updatePassword(password)

    suspend fun sendFavorite(favorite: FavoriteDto): FavoriteDto = api.sendFavorite(favorite)
}

