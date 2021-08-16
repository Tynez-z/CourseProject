package com.example.firebaseauthentication.data.repository

import com.example.firebaseauthentication.data.FireBaseSource
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryLogin @Inject constructor(private val fireBaseSource: FireBaseSource) :
    RegisterRepository {

    override suspend fun signUpUser(email: String, password: String, fullName: String) =
        fireBaseSource.signUpUser(email, password, fullName)

    override suspend fun signInUser(email: String, password: String) =
        fireBaseSource.signInUser(email, password)

    override suspend fun saveUser(email: String, name: String) =
        fireBaseSource.saveUser(email, name)

    override suspend fun sendForgotPassword(email: String) =
        fireBaseSource.sendForgotPassword(email)

    override suspend fun fetchUser() = fireBaseSource.fetchUser()

    override suspend fun signInWithGoogle(acct: GoogleSignInAccount) = fireBaseSource.signInWithGoogle(acct)
}