package com.example.firebaseauthentication.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FireBaseSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore) {

    fun signUpUser(email: String, password: String, fullName: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password)

    fun signInUser(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password)
//TODO constants
    fun saveUser(email: String, name: String) =
        fireStore.collection("users").document(email).set(User(email = email, fullName = name))

    fun fetchUser() = fireStore.collection("users").get()
    fun sendForgotPassword(email: String) = firebaseAuth.sendPasswordResetEmail(email)
}