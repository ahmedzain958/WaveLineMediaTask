package com.wavelinemedia.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.wavelinemedia.data.Notification.CHANNEL_ID
import com.wavelinemedia.data.Notification.CHANNEL_NAME
import com.wavelinemedia.presentation.ActivityA
import com.wavelinemedia.task.R

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters,
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val notificationId = inputData.getInt("NOTIFICATION_ID", 0)
        val title = inputData.getString("NOTIFICATION_TITLE") ?: "Scheduled Notification"
        val message =
            inputData.getString("NOTIFICATION_MESSAGE") ?: "Your notification has arrived!"
        showNotification(notificationId, title, message)

        return Result.success()
    }

    private fun showNotification(notificationId: Int, title: String, message: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    applicationContext,
                    notificationId,
                    Intent(applicationContext, ActivityA::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    },
                    PendingIntent.FLAG_UPDATE_CURRENT  or PendingIntent.FLAG_IMMUTABLE
                )
            )
            .build()

        notificationManager.notify(notificationId, notification)
    }
}

object Notification {
    const val CHANNEL_ID = "default_channel"
    const val CHANNEL_NAME = "Wave Line Media"
}
