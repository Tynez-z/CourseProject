package com.example.firebaseauthentication.domain.interactor.usecase.login

import com.example.firebaseauthentication.data.repository.RegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveUserUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend fun execute(mail: String, name: String) = registerRepository.saveUser(mail, name)
}