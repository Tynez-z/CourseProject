package com.example.firebaseauthentication.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationCompat
import com.example.firebaseauthentication.MainActivity
import com.example.firebaseauthentication.R

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
//    val actionIntent = Intent()
    val contentPendingIntent = PendingIntent.getActivity(applicationContext, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    val builder = NotificationCompat.Builder(applicationContext,applicationContext.getString(R.string.channel_id)
    )
        .setSmallIcon(R.drawable.ic_play_button)
//        .setLargeIcon(BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_filmroll))
        .setContentTitle(applicationContext.getString(R.string.title_notification))
        .setContentText(messageBody)
        .setVibrate(LongArray(3))
        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
        .setContentIntent(contentPendingIntent)
        .setPriority(NotificationCompat.PRIORITY_MAX)
//        .setLights()
        .setAutoCancel(true)
    notify(NOTIFICATION_ID, builder.build())
}

fun Context.createNotificationsChanel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(getString(R.string.channel_id), getString(R.string.channel_name), NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.apply {
            notificationChannel.enableLights(true)
            notificationChannel.shouldShowLights()
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Example"
        }
        notificationManager.createNotificationChannel(notificationChannel)
    }
}
