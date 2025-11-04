package com.beratbaran.loopa.domain.repository

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
}