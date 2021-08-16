package com.example.firebaseauthentication.domain.interactor.usecase.login

import com.example.firebaseauthentication.data.repository.RegisterRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import javax.inject.Inject

class SignInGoogleUseCase @Inject constructor(val registerRepository: RegisterRepository) {
    suspend fun execute(acct: GoogleSignInAccount) = registerRepository.signInWithGoogle(acct)
}