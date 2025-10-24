package com.beratbaran.loopa.data.remote.api

import com.beratbaran.loopa.data.remote.dto.CategoryDto
import com.beratbaran.loopa.data.remote.dto.FavoriteDto
import com.beratbaran.loopa.data.remote.dto.PlaceDto
import com.beratbaran.loopa.data.remote.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface LoopaApi {
    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("places")
    suspend fun getPlaces(): List<PlaceDto>

    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("favorites")
    suspend fun getFavorites(): List<FavoriteDto>

    @POST("users")
    suspend fun createUser(@Body user: UserDto): UserDto

    @PUT("users")
    suspend fun updatePassword(@Body user: UserDto): UserDto

    @POST("favorites")
    suspend fun sendFavorite(@Body favorite: FavoriteDto): FavoriteDto
}
