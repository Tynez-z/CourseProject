package com.example.firebaseauthentication.domain.interactor.usecase.login

import com.example.firebaseauthentication.data.repository.RegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchUserUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend fun execute() = registerRepository.fetchUser()
}