package com.example.firebaseauthentication.presentation.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthentication.data.NetworkControl
import com.example.firebaseauthentication.data.Resource
import com.example.firebaseauthentication.data.User
import com.example.firebaseauthentication.domain.interactor.usecase.login.SaveUserUseCase
import com.example.firebaseauthentication.domain.interactor.usecase.login.SignUpUserUseCase
import com.example.firebaseauthentication.utils.CONDITION_PASSWORD
import com.example.firebaseauthentication.utils.EMAIL_EXIST
import com.example.firebaseauthentication.utils.FIELD_MUST_FILL
import com.example.firebaseauthentication.utils.NO_INTERNET
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val networkControl: NetworkControl,
    private val firebaseAuth: FirebaseAuth,
    private val signUpUserUseCase: SignUpUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    private val userLiveData = MutableLiveData<Resource<User>>()
    private val saveUserLiveData = MutableLiveData<Resource<User>>()

    fun signUpUser(email: String, password: String, fullName: String): LiveData<Resource<User>> {
        when {
            TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(fullName) -> {
                userLiveData.postValue(Resource.error(null, FIELD_MUST_FILL))
            }
            TextUtils.isEmpty(email) -> {
                userLiveData.postValue(Resource.error(null, FIELD_MUST_FILL))
            }
            TextUtils.isEmpty(fullName) -> {
                userLiveData.postValue(Resource.error(null, FIELD_MUST_FILL))
            }
            password.length < 8 -> {
                userLiveData.postValue(Resource.error(null, CONDITION_PASSWORD))
            }
            networkControl.isConnected() -> {
                userLiveData.postValue(Resource.loading(null))
                firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
                    if (it.result?.signInMethods?.size == 0) {
                        viewModelScope.launch {
                            signUpUserUseCase.execute(email, password, fullName)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        firebaseAuth.currentUser?.sendEmailVerification()
                                        userLiveData.postValue(
                                            Resource.success(
                                                User(email = email, fullName = fullName)))
                                    } else {
                                        userLiveData.postValue(
                                            Resource.error(null, it.exception?.message.toString()))
                                    }
                                }
                        }
                    } else {
                        userLiveData.postValue(Resource.error(null, EMAIL_EXIST))
                    }
                }
            }
            else -> {
                userLiveData.postValue(Resource.error(null, NO_INTERNET))
            }
        }
        return userLiveData
    }

    fun saveUser(email: String, name: String) {
        viewModelScope.launch {
            saveUserUseCase.execute(email, name).addOnCompleteListener {
                if (it.isSuccessful) {
                    saveUserLiveData.postValue(Resource.success(User(email, name)))
                } else {
                    saveUserLiveData.postValue(
                        Resource.error(null, it.exception?.message.toString()))
                }
            }
        }
    }
}