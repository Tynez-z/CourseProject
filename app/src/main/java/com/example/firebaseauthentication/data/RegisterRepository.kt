package com.example.firebaseauthentication.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.QuerySnapshot

interface RegisterRepository {
    suspend fun signUpUser(email: String, password: String, fullName: String): Task<AuthResult>
    suspend fun signInUser(email: String, password: String): Task<AuthResult>
    suspend fun saveUser(email: String, name: String): Task<Void>
    suspend fun fetchUser(): Task<QuerySnapshot>
    suspend fun sendForgotPassword(email: String): Task<Void>
}