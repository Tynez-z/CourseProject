package com.example.firebaseauthentication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashScreenFragment : BaseFragment(R.layout.fragment_splash_screen), CoroutineScope {

    private lateinit var binding: FragmentSplashScreenBinding

    companion object {
        private const val TIME: Long = 2800
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            delay(TIME)
            withContext(Dispatchers.Main) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }
        }
    }
}