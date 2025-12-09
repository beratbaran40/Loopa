package com.beratbaran.loopa.data.remote.api

import com.beratbaran.loopa.data.model.CategoryResponseDto
import com.beratbaran.loopa.data.model.FavoriteDto
import com.beratbaran.loopa.data.model.PlaceDetailsResponseDto
import com.beratbaran.loopa.data.model.PlaceResponseDto
import com.beratbaran.loopa.data.model.RegisterRequest
import com.beratbaran.loopa.data.model.RegisterResponse
import com.beratbaran.loopa.data.model.UserDto
import com.beratbaran.loopa.data.model.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface LoopaApi {
    @GET("places")
    suspend fun getPlaces(): PlaceResponseDto

    @GET("users")
    suspend fun getUser(@Query("uid") uid: String): UserResponseDto

    @GET("places/{placeId}")
    suspend fun getPlaceDetail(
        @Path("placeId") placeId: Int,
    ): PlaceDetailsResponseDto

    @GET("categories")
    suspend fun getCategories(): CategoryResponseDto

    @GET("favorites")
    suspend fun getFavorites(): List<FavoriteDto>

    @POST("users")
    suspend fun createUser(@Body user: RegisterRequest): RegisterResponse

    @PUT("users")
    suspend fun updatePassword(@Body user: UserDto): UserDto

    @POST("favorites")
    suspend fun sendFavorite(@Body favorite: FavoriteDto): FavoriteDto
}
