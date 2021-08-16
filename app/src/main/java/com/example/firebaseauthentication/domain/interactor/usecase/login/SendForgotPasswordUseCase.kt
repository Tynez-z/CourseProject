package com.example.firebaseauthentication.domain.interactor.usecase.login

import com.example.firebaseauthentication.data.repository.RegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SendForgotPasswordUseCase @Inject constructor(val registerRepository: RegisterRepository) {
    suspend fun execute(email: String) = registerRepository.sendForgotPassword(email)
}