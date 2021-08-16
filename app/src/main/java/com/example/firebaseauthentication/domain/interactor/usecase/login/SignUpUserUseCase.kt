package com.example.firebaseauthentication.domain.interactor.usecase.login

import com.example.firebaseauthentication.data.repository.RegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpUserUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend fun execute(email: String, password: String, fullName: String) = registerRepository.signUpUser(email, password, fullName)
}