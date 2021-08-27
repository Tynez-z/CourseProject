package com.example.firebaseauthentication.presentation.viewmodel

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.utils.sendNotification
import javax.inject.Inject

//class NotificationViewModel @Inject constructor(private val app: Application) : ViewModel() {
//
//    private val notificationManager =
//        ContextCompat.getSystemService(app, NotificationManager::class.java) as NotificationManager
//
//    fun sendNotification() {
//        notificationManager.sendNotification(app.getString(R.string.message), app)
//    }
//}