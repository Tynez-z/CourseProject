package com.example.firebaseauthentication.domain.interactor.usecase

import com.example.firebaseauthentication.data.RegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInUserUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend fun signInUser(email: String, password: String) = registerRepository.signInUser(email, password)
}