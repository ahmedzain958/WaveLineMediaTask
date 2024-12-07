package com.wavelinemedia.domain

import com.wavelinemedia.task.domain.NotificationRepository
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(): List<Notification> = repository.getNotifications()
}
