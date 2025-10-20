package com.beratbaran.loopa.data.repository

import com.beratbaran.loopa.data.remote.api.LoopaApi
import com.beratbaran.loopa.data.remote.dto.CategoryDto
import com.beratbaran.loopa.data.remote.dto.FavoriteDto
import com.beratbaran.loopa.data.remote.dto.PlaceDto
import com.beratbaran.loopa.data.remote.dto.UserDto
import javax.inject.Inject

class PlaceRepository @Inject constructor(
    private val api: LoopaApi,
) {
    suspend fun getUsers(): List<UserDto> = api.getUsers()

    suspend fun getPlaces(): List<PlaceDto> = api.getPlaces()

    suspend fun getCategories(): List<CategoryDto> = api.getCategories()

    suspend fun getFavorites(): List<FavoriteDto> = api.getFavorites()

    suspend fun createUser(user: UserDto): UserDto = api.createUser(user)

    suspend fun sendFavorite(favorite: FavoriteDto): FavoriteDto = api.sendFavorite(favorite)
}

