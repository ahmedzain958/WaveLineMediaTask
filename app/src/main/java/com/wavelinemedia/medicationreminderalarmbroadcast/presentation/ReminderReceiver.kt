package com.wavelinemedia.medicationreminderalarmbroadcast.presentation

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.model.Reminder
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.use_cases.UpdateUseCase
import com.wavelinemedia.task.CHANNEL
import com.wavelinemedia.task.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

const val DONE = "DONE"
const val REJECT = "REJECT"

@AndroidEntryPoint
class ReminderReceiver : BroadcastReceiver() {

    @Inject
    lateinit var updateUseCase: UpdateUseCase
    lateinit var mediaPlayer: MediaPlayer
    override fun onReceive(context: Context, intent: Intent) {
        mediaPlayer = MediaPlayer.create(context, R.raw.alarm_music)
        val reminderJson = intent.getStringExtra(REMINDER)
        val reminder = Gson().fromJson(reminderJson, Reminder::class.java)

        val doneIntent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra(REMINDER, reminderJson)
            action = DONE
        }
        val donePendingIntent =
            PendingIntent.getBroadcast(
                context,
                reminder.timeInMillis.toInt(),
                doneIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

        val closeIntent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra(REMINDER, reminderJson)
            action = REJECT
        }
        val closePendingIntent =
            PendingIntent.getBroadcast(
                context,
                reminder.timeInMillis.toInt(),
                closeIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

        when (intent.action) {
            DONE -> {
                runBlocking {
                    updateUseCase(reminder.copy(isTaken = true))
                    cancelAlarm(context, reminder)
                }
            }

            REJECT -> {
                runBlocking {
                    updateUseCase(reminder.copy(isTaken = true))
                    cancelAlarm(context, reminder)
                }
            }

            else -> {
                showNotification(context, reminder, donePendingIntent, closePendingIntent)
                mediaPlayer.release()
                mediaPlayer.start()
            }
        }

    }

    private fun showNotification(
        context: Context,
        reminder: Reminder,
        donePendingIntent: PendingIntent?,
        closePendingIntent: PendingIntent?,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {

                val notification = NotificationCompat.Builder(context, CHANNEL)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Medication Reminder")
                    .setContentText(reminder.name.plus(" ${reminder.dosage}"))
                    .addAction(R.drawable.ic_check, "Done", donePendingIntent)
                    .addAction(R.drawable.ic_close, "Close", closePendingIntent)
                    .build()
                NotificationManagerCompat.from(context)
                    .notify(1, notification)
            }
        } else {
            val notification = NotificationCompat.Builder(context, CHANNEL)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Medication Reminder")
                .setContentText(reminder.name.plus(" ${reminder.dosage}"))
                .addAction(R.drawable.ic_check, "Done", donePendingIntent)
                .addAction(R.drawable.ic_close, "Close", closePendingIntent)
                .build()
            NotificationManagerCompat.from(context)
                .notify(1, notification)
        }
    }

}