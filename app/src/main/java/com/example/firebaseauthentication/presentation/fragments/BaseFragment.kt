package com.example.firebaseauthentication.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.App
import com.example.firebaseauthentication.utils.RC_SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import javax.inject.Inject

abstract class BaseFragment(val layoutId: Int) : Fragment(layoutId) {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProvider(this, viewModelProvider)[T::class.java]
        vm.body()
        return vm
    }

    fun onCLickSignInGoogle() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
}