package com.example.firebaseauthentication.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val fireBaseSource: FireBaseSource) :
    RegisterRepository {

    override suspend fun signUpUser(email: String, password: String, fullName: String) =
        fireBaseSource.signUpUser(email, password, fullName)

    override suspend fun signInUser(email: String, password: String) =
        fireBaseSource.signInUser(email, password)

    override suspend fun saveUser(email: String, name: String) =
        fireBaseSource.saveUser(email, name)

    override suspend fun fetchUser() = fireBaseSource.fetchUser()

    override suspend fun sendForgotPassword(email: String) =
        fireBaseSource.sendForgotPassword(email)
}