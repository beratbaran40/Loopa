package com.beratbaran.loopa.di

import com.beratbaran.loopa.data.repository.UserRepositoryImpl
import com.beratbaran.loopa.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(auth: FirebaseAuth): UserRepository = UserRepositoryImpl(auth)
}