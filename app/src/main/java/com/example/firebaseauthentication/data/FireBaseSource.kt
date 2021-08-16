package com.example.firebaseauthentication.data

import com.example.firebaseauthentication.utils.USERS
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FireBaseSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) {

    fun signUpUser(email: String, password: String, fullName: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password)

    fun signInUser(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password)

    fun saveUser(email: String, name: String) =
        fireStore.collection(USERS).document(email).set(User(email = email, fullName = name))

    fun sendForgotPassword(email: String) = firebaseAuth.sendPasswordResetEmail(email)

    fun fetchUser() = fireStore.collection(USERS).get()

    fun signInWithGoogle(acct: GoogleSignInAccount) = firebaseAuth.signInWithCredential(
        GoogleAuthProvider.getCredential(acct.idToken, null))
}