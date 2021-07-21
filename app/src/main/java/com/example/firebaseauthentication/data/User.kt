package com.example.firebaseauthentication.data

data class User(val email: String = "", val fullName: String = "", val type: TYPE = TYPE.PATIENT) {

    enum class TYPE {
        PATIENT
    }
}