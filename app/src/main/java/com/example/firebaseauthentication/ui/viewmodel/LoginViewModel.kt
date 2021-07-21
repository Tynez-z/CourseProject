package com.example.firebaseauthentication.ui.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthentication.data.NetworkControl
import com.example.firebaseauthentication.data.Resource
import com.example.firebaseauthentication.data.User
import com.example.firebaseauthentication.domain.interactor.usecase.FetchUserUseCase
import com.example.firebaseauthentication.domain.interactor.usecase.SendForgotPasswordUseCase
import com.example.firebaseauthentication.domain.interactor.usecase.SignInUserUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val signInUserUseCase: SignInUserUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val sendForgotPasswordUseCase: SendForgotPasswordUseCase,
    private val networkControl: NetworkControl,
    private val firebaseAuth: FirebaseAuth) : ViewModel() {

    private val userLiveData = MutableLiveData<Resource<User>>()
    private val sendResetPasswordLiveData = MutableLiveData<Resource<User>>()

    //TODO constants
    fun signInUser(email: String, password: String): LiveData<Resource<User>> {
        when {
            TextUtils.isEmpty(email) && TextUtils.isEmpty(password) -> {
                userLiveData.postValue(Resource.error(null, "Enter email and password"))
            }
            networkControl.isConnected() -> {
                userLiveData.postValue(Resource.loading(null))
                firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
                    if (it.result?.signInMethods?.size == 0) {
                        userLiveData.postValue(Resource.error(null, "Email does not exist"))
                    } else {
                        viewModelScope.launch {
                            signInUserUseCase.signInUser(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        firebaseAuth.currentUser?.isEmailVerified?.let { verified ->
                                            if (verified) {
                                                viewModelScope.launch {
                                                    fetchUserUseCase.fetchUser()
                                                        .addOnCompleteListener { userTask ->
                                                            if (userTask.isSuccessful) {
                                                                userTask.result?.documents?.forEach {
                                                                    if (it.data!!["email"] == email) {
                                                                        val name =
                                                                            it.data?.getValue("fullName")
                                                                        userLiveData.postValue(
                                                                            Resource.success(
                                                                                User(
                                                                                    firebaseAuth.currentUser?.email!!,
                                                                                    name?.toString()!!))) } }
                                                            } else {
                                                                userLiveData.postValue(
                                                                    Resource.error(
                                                                        null, userTask.exception?.message.toString())) } } }
                                            } else {
                                                userLiveData.postValue(
                                                    Resource.error(
                                                        null, "Email is not verified, check your email")) } }
                                    } else {
                                        userLiveData.postValue(
                                            Resource.error(
                                                null, task.exception?.message.toString())
                                        )
                                        Timber.e(task.exception.toString()) } } } } } }
            else -> {
                userLiveData.postValue(Resource.error(null, "No internet connection"))
            }
        }
        return userLiveData
    }

    fun sendResetPassword(email: String): LiveData<Resource<User>> {
        when {
            TextUtils.isEmpty(email) -> {
                sendResetPasswordLiveData.postValue(Resource.error(null, "Enter registered email"))
            }
            networkControl.isConnected() -> {
                viewModelScope.launch {
                    sendForgotPasswordUseCase.sendForgotPassword(email).addOnCompleteListener { task ->
                        sendResetPasswordLiveData.postValue(Resource.loading(null))
                        if (task.isSuccessful) {
                            sendResetPasswordLiveData.postValue(Resource.success(User()))
                        } else {
                            sendResetPasswordLiveData.postValue(
                                Resource.error(
                                    null, task.exception?.message.toString()
                                )
                            )
                        }
                    }
                }
            }
            else -> {
                sendResetPasswordLiveData.postValue(Resource.error(null, "No internet connection"))
            }
        }
        return sendResetPasswordLiveData
    }
}