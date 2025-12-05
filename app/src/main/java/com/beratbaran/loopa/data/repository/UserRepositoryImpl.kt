package com.beratbaran.loopa.data.repository

import android.util.Log
import com.beratbaran.loopa.data.model.LoginRequest
import com.beratbaran.loopa.data.model.RegisterRequest
import com.beratbaran.loopa.data.remote.api.LoopaApi
import com.beratbaran.loopa.domain.UserModel
import com.beratbaran.loopa.domain.repository.UserRepository
import com.beratbaran.loopa.domain.toDomain
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val loopaApi: LoopaApi,
    private var currentUser: UserModel? = null,
) : UserRepository {

    override suspend fun registerUser(
        name: String,
        surname: String,
        email: String,
        password: String,
    ): Result<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = firebaseAuth.currentUser ?: throw Exception("User registration failed")
            val registerRequest = RegisterRequest(
                uid = user.uid,
                name = name,
                surname = surname,
                email = email,
            )
            loopaApi.createUser(registerRequest)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loginUser(
        email: String,
        password: String,
    ): Result<Unit> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = firebaseAuth.currentUser ?: throw Exception("User login failed")
            LoginRequest(
                uid = user.uid,
                email = email,
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loadCurrentUser(): Result<UserModel> {
        return try {
            val firebaseUser = firebaseAuth.currentUser
                ?: throw Exception("User not logged in")

            val uid = firebaseUser.uid
            Log.d("UserRepositoryImpl", "loadCurrentUser uid : $uid")

            val response = loopaApi.getUser(uid)

            val userModel = response.data.toDomain()

            currentUser = userModel

            Result.success(userModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCurrentUser(): UserModel? = currentUser
}