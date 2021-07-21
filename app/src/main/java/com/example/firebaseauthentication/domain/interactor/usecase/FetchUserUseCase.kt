package com.example.firebaseauthentication.domain.interactor.usecase

import com.example.firebaseauthentication.data.RegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchUserUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend fun fetchUser() = registerRepository.fetchUser()
}