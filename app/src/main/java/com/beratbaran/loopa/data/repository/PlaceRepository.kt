package com.beratbaran.loopa.data.repository

import android.util.Log
import com.beratbaran.loopa.data.model.FavoriteDto
import com.beratbaran.loopa.data.model.PlaceResponseDto
import com.beratbaran.loopa.data.model.UserDto
import com.beratbaran.loopa.data.model.toDetailModel
import com.beratbaran.loopa.data.model.toModel
import com.beratbaran.loopa.data.remote.api.LoopaApi
import com.beratbaran.loopa.domain.PlaceDetailModel
import com.beratbaran.loopa.domain.PlaceResponseModel
import com.beratbaran.loopa.ui.homepage.PlaceModel
import javax.inject.Inject

class PlaceRepository @Inject constructor(
    private val loopaApi: LoopaApi,
) {
    suspend fun getPlaces(): PlaceResponseModel {
        val response: PlaceResponseDto = loopaApi.getPlaces()
        val data = response.data

        Log.d("PlaceRepository", "dto top=${data?.topPlaces?.size}")
        Log.d("PlaceRepository", "dto want=${data?.wantToLookPlaces?.size}")

        val top = data?.topPlaces ?: emptyList()
        val want = data?.wantToLookPlaces ?: emptyList()

        Log.d("PlaceRepository", "HOME top count = ${top.size}")
        Log.d("PlaceRepository", "HOME want count = ${want.size}")

        Log.d(
            "PlaceRepository",
            "HOME top ids = ${top.map { it.id }}"
        )
        Log.d(
            "PlaceRepository",
            "HOME want ids = ${want.map { it.id }}"
        )

        return PlaceResponseModel(
            topPlaces = (data?.topPlaces ?: emptyList()).map { it.toModel() },
            wantToLookPlaces = (data?.wantToLookPlaces ?: emptyList()).map { it.toModel() },
        )
    }

    suspend fun getCategoryPlaces(categoryId: Int): List<PlaceModel> {
        val response: PlaceResponseDto = loopaApi.getPlaces()
        val data = response.data
        val allDtos = (data?.topPlaces ?: emptyList()) + (data?.wantToLookPlaces ?: emptyList())
        val allPlaces = allDtos.map { it.toModel() }

        Log.d("Places Repository", "getCategoryPlaces() id=$categoryId allIds=${allPlaces.map { it.categoryId }}")

        //val filtered = allPlaces.filter { it.categoryId == categoryId }

        //Log.d("Places Repository", "getCategoryPlaces() filtered size=${filtered.size}")
        Log.d("Places Repository", "first dto=${allDtos.firstOrNull()}")

        return allPlaces
    }

    suspend fun getPlaceDetail(placeId: Int): PlaceDetailModel {
        Log.d("PlaceRepository", "DETAIL request id = $placeId")
        val response = loopaApi.getPlaceDetail(placeId)
        val data = response.data
        return data?.toDetailModel()
            ?: PlaceDetailModel(
                id = 0,
                name = "",
                description = "",
                location = "",
                rating = 0.0,
                categoryId = 0,
                categoryName = "",
                latitude = null,
                longitude = null,
                images = emptyList(),
                isFavorite = false
            )
    }

    suspend fun getFavorites(): List<FavoriteDto> = loopaApi.getFavorites()

    suspend fun updatePassword(password: UserDto): UserDto = loopaApi.updatePassword(password)

    suspend fun sendFavorite(favorite: FavoriteDto): FavoriteDto = loopaApi.sendFavorite(favorite)
}

