package com.example.firebaseauthentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.firebaseauthentication.databinding.ActivityMainBinding
import com.example.firebaseauthentication.utils.makeGone
import com.example.firebaseauthentication.utils.makeVisible

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        workWithBottomNavigation()
    }

    private fun workWithBottomNavigation() {
        activityMainBinding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.signUpFragment, R.id.splashScreenFragment -> {
                    activityMainBinding.bottomNavigationView.makeGone()
                }
                else -> {
                    activityMainBinding.bottomNavigationView.makeVisible()
                }
            }
        }
    }
}