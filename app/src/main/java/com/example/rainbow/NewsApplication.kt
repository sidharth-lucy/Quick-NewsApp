package com.example.rainbow

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.rainbow.baseUtils.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                Constants.NOTIFICATION_CHANNEL_ID,
                "Music Player1",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "This is to show song notification"
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}