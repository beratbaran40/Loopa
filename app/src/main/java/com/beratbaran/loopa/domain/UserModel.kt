package com.beratbaran.loopa.domain

import com.beratbaran.loopa.data.model.UserDto

data class UserModel(
    val uid: String,
    val name: String,
    val surname: String,
    val fullName: String,
    val email: String,
)

fun UserDto.toDomain(): UserModel =
    UserModel(
        uid = uid,
        name = name.orEmpty(),
        surname = surname.orEmpty(),
        fullName = fullName.orEmpty(),
        email = email,
    )