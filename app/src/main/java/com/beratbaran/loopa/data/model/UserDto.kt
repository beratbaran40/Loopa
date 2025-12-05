package com.beratbaran.loopa.data.model

data class UserResponseDto(
    val code: Int,
    val message: String,
    val data: UserDto,
)

data class UserDto(
    val uid: String,
    val email: String,
    val name: String?,
    val surname: String?,
    val fullName: String?,
)