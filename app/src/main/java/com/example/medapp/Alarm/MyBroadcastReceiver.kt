package com.example.medapp.Alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.medapp.R

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var builder : NotificationCompat.Builder = NotificationCompat.Builder(context!!,"notifyLemubit")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("HOLA")
            .setContentText("HOLA 2")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        var notificationManager : NotificationManagerCompat = NotificationManagerCompat.from(context)

        notificationManager.notify(200,builder.build())
    }
}