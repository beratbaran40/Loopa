package com.beratbaran.loopa.domain.repository

import com.beratbaran.loopa.domain.UserModel

interface UserRepository {
    suspend fun registerUser(
        name: String,
        surname: String,
        email: String,
        password: String,
    ): Result<Unit>

    suspend fun loginUser(
        email: String,
        password: String,
    ): Result<Unit>

    suspend fun loadCurrentUser(): Result<UserModel>

    fun getCurrentUser(): UserModel?
}