package com.wavelinemedia.data

import com.wavelinemedia.domain.Notification
import com.wavelinemedia.task.domain.NotificationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepositoryImpl @Inject constructor(
    private val xmlParser: NotificationXmlParser,
    private val notificationScheduler: NotificationScheduler
) : NotificationRepository {

    override suspend fun getNotifications(): List<Notification> = xmlParser.parseNotifications()

    override fun scheduleNotification(notification: Notification) {
        notificationScheduler.schedule(notification)
    }

    override fun cancelNotification(notificationId: Int) {
        notificationScheduler.cancel(notificationId)
    }

    override fun cancelAllNotifications() {
        notificationScheduler.cancelAll()
    }
}
