package com.wavelinemedia.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wavelinemedia.domain.GetNotificationsUseCase
import com.wavelinemedia.domain.Notification
import com.wavelinemedia.domain.ScheduleNotificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val scheduleNotificationUseCase: ScheduleNotificationUseCase
) : ViewModel() {

    private val _notifications = MutableLiveData<List<Notification>>()
    val notifications: LiveData<List<Notification>> get() = _notifications

    fun loadNotifications() {
        viewModelScope.launch {
            _notifications.value = getNotificationsUseCase()
        }
    }

    fun cancelAllNotifications() {
        scheduleNotificationUseCase.cancelAll()
    }

    fun scheduleNotification(notification: Notification) {
        scheduleNotificationUseCase.schedule(notification)
    }

    fun cancelNotification(notificationId: Int) {
        scheduleNotificationUseCase.cancel(notificationId)
    }
}
