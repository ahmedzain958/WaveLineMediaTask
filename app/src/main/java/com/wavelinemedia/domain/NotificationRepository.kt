package com.wavelinemedia.task.domain

import com.wavelinemedia.domain.Notification

interface NotificationRepository {
    suspend fun getNotifications(): List<Notification>
    fun scheduleNotification(notification: Notification)
    fun cancelNotification(notificationId: Int)
    fun cancelAllNotifications()
}
