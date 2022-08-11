package com.dicoding.kelassekolah

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService

class NotificationManager(val context: Context) {
    private lateinit var notificationChannel: NotificationChannel

    private val channelId = "com.dicoding.kelassekolah.taskreminder"
    private val description = "Notifikasi Pengingat Tugas"

    fun startNotification(notificationTitle: String, notificationText: String) {
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground_classroom)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, notificationTitle, NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.WHITE
            notificationChannel.enableVibration(false)
            manager.createNotificationChannel(notificationChannel)
        }

        manager.notify(R.id.cb_task_notification, builder.build())
    }
}