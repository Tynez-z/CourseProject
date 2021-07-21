package com.example.firebaseauthentication.domain.interactor.usecase

import com.example.firebaseauthentication.data.RegisterRepository
import javax.inject.Inject
import javax.inject.Singleton
//TODO rename execute

@Singleton
class SaveUserUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend fun execute(mail: String, name: String) = registerRepository.saveUser(mail, name)
}