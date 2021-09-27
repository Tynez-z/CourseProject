package com.example.firebaseauthentication.presentation.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthentication.data.NetworkControl
import com.example.firebaseauthentication.data.Resource
import com.example.firebaseauthentication.data.User
import com.example.firebaseauthentication.domain.interactor.usecase.login.FetchUserUseCase
import com.example.firebaseauthentication.domain.interactor.usecase.login.SendForgotPasswordUseCase
import com.example.firebaseauthentication.domain.interactor.usecase.login.SignInGoogleUseCase
import com.example.firebaseauthentication.domain.interactor.usecase.login.SignInUserUseCase
import com.example.firebaseauthentication.utils.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val signInUserUseCase: SignInUserUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val signInGoogleUseCase: SignInGoogleUseCase,
    private val sendForgotPasswordUseCase: SendForgotPasswordUseCase,

    private val networkControl: NetworkControl,
    private val firebaseAuth: FirebaseAuth) : ViewModel() {

    private val userLiveData = MutableLiveData<Resource<User>>()
    private val sendResetPasswordLiveData = MutableLiveData<Resource<User>>()
    private val gMailUserLiveData = MutableLiveData<Resource<User>>()

    fun signInUser(email: String, password: String): LiveData<Resource<User>> {
        when {
            TextUtils.isEmpty(email) && TextUtils.isEmpty(password) -> {
                userLiveData.postValue(Resource.error(null, ENTER_EMAIL_PASSWORD))
            }
            TextUtils.isEmpty(email) -> {
                userLiveData.postValue(Resource.error(null, FIELD_MUST_FILL))
            }
            TextUtils.isEmpty(password) -> {
                userLiveData.postValue(Resource.error(null, FIELD_MUST_FILL))
            }
            networkControl.isConnected() -> {
                userLiveData.postValue(Resource.loading(null))
                firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener { it ->
                    if (it.result?.signInMethods?.size == 0) {
                        userLiveData.postValue(Resource.error(null, EMAIL_NOT_EXIST))
                    } else {
                        viewModelScope.launch {
                            signInUserUseCase.execute(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        firebaseAuth.currentUser?.isEmailVerified?.let { verified ->
                                            if (verified) {
                                                viewModelScope.launch {
                                                    fetchUserUseCase.execute().addOnCompleteListener { userTask ->
                                                            if (userTask.isSuccessful) {
                                                                userTask.result?.documents?.forEach {
                                                                    if (it.data!![EMAIL] == email) {
                                                                        val name = it.data?.getValue(FULL_NAME)
                                                                        userLiveData.postValue(
                                                                            Resource.success(User(firebaseAuth.currentUser?.email!!,
                                                                                    name?.toString()!!))) } }
                                                            } else {
                                                                userLiveData.postValue(
                                                                    Resource.error(
                                                                        null, userTask.exception?.message.toString())) } } }
                                            } else {
                                                userLiveData.postValue(
                                                    Resource.error(
                                                        null, EMAIL_NOT_VERIFIED)) } }
                                    } else {
                                        userLiveData.postValue(
                                            Resource.error(
                                                null, task.exception?.message.toString())
                                        )
                                        Timber.e(task.exception.toString()) } } } } } }
            else -> {
                userLiveData.postValue(Resource.error(null, NO_INTERNET))
            }
        }
        return userLiveData
    }

    fun sendResetPassword(email: String): LiveData<Resource<User>> {
        Log.i("TAG", email.toString())
        if (TextUtils.isEmpty(email)) {
            sendResetPasswordLiveData.postValue(Resource.error(null, ENTER_REGISTERED_EMAIL))
        }
        when {
            networkControl.isConnected() -> {
                viewModelScope.launch {
                    sendForgotPasswordUseCase.execute(email).addOnCompleteListener { task ->
                        sendResetPasswordLiveData.postValue(Resource.loading(null))
                        if (task.isSuccessful) {
                            sendResetPasswordLiveData.postValue(Resource.success(User()))
                        } else {
                            sendResetPasswordLiveData.postValue(
                                Resource.error(null, task.exception?.message.toString()))
                        }
                    }
                }
            } else -> {
                sendResetPasswordLiveData.postValue(Resource.error(null, NO_INTERNET))
            }
        }
        return sendResetPasswordLiveData
    }

    fun signInWithGoogle(acct: GoogleSignInAccount): LiveData<Resource<User>> {
        viewModelScope.launch {
            signInGoogleUseCase.execute(acct).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    gMailUserLiveData.postValue(Resource.success(User(firebaseAuth.currentUser?.email!!,
                        firebaseAuth.currentUser?.displayName!!)))
                } else {
                    gMailUserLiveData.postValue(Resource.error(null, COULD_NOT_SIGN_IN))
                }
            }
        }
        return gMailUserLiveData
    }
}