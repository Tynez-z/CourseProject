package com.example.firebaseauthentication.domain.interactor.usecase

import com.example.firebaseauthentication.data.RegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SendForgotPasswordUseCase @Inject constructor(val registerRepository: RegisterRepository) {
    suspend fun execute(email: String) = registerRepository.sendForgotPassword(email)
}