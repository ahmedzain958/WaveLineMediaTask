package com.wavelinemedia.medicationreminderalarmbroadcast.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.model.Reminder

const val REMINDER = "REMINDER"

/*for execution one time, for periodic, another fn will be created*/
fun setUpAlarm(context: Context, reminder: Reminder) {
    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra(REMINDER, Gson().toJson(reminder))
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        reminder.timeInMillis.toInt(),
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    try {
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            reminder.timeInMillis,
            pendingIntent
        )
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}

fun cancelAlarm(context: Context, reminder: Reminder) {
    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra(REMINDER, Gson().toJson(reminder))
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        reminder.timeInMillis.toInt(),
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    try {
        alarmManager.cancel(
            pendingIntent
        )
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}

fun setUpPeriodicAlarm(context: Context, reminder: Reminder) {
    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra(REMINDER, Gson().toJson(reminder))
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        reminder.timeInMillis.toInt(),
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    try {
        val interval = 2L * 60 * 1000
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            reminder.timeInMillis,
            interval,
            pendingIntent
        )
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}