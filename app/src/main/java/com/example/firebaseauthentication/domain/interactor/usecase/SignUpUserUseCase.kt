package com.example.firebaseauthentication.domain.interactor.usecase

import com.example.firebaseauthentication.data.RegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpUserUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend fun execute(email: String, password: String, fullName: String) = registerRepository.signUpUser(email, password, fullName)
}