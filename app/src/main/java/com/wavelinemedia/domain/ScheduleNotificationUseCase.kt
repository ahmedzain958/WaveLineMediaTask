package com.wavelinemedia.domain

import com.wavelinemedia.task.domain.NotificationRepository
import javax.inject.Inject

class ScheduleNotificationUseCase @Inject constructor(
    private val repository: NotificationRepository
) {
    fun schedule(notification: Notification) = repository.scheduleNotification(notification)
    fun cancel(notificationId: Int) = repository.cancelNotification(notificationId)
    fun cancelAll() = repository.cancelAllNotifications()
}
