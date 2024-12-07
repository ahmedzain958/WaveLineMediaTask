package com.wavelinemedia.data

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.wavelinemedia.domain.Notification
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationScheduler @Inject constructor(
    private val workManager: WorkManager
) {
    fun schedule(notification: Notification) {
        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(notification.timeInSeconds.toLong(), TimeUnit.SECONDS)
            .addTag("NOTIF_${notification.id}")
            .build()
        workManager.enqueue(workRequest)
    }

    fun cancel(notificationId: Int) {
        workManager.cancelAllWorkByTag("NOTIF_$notificationId")
    }

    fun cancelAll() {
        workManager.cancelAllWork()
    }
}
