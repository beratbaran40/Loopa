package com.beratbaran.loopa.data.remote.dto

data class UserDto(
    val name: String?,
    val surname: String?,
    val fullName: String?,
    val email: String,
    val password: String,
)