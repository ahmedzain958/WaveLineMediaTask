package com.wavelinemedia.medicationreminderalarmbroadcast.data.repository

import com.wavelinemedia.medicationreminderalarmbroadcast.data.local.ReminderDao
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.model.Reminder
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow

class ReminderRepoImpl(private val reminderDao: ReminderDao) : ReminderRepository {
    override suspend fun insert(reminder: Reminder) {
        reminderDao.insert(reminder)
    }

    override suspend fun update(reminder: Reminder) {
        reminderDao.update(reminder)
    }

    override suspend fun delete(reminder: Reminder) {
        reminderDao.delete(reminder)

    }

    override fun getAllReminders(): Flow<List<Reminder>> = reminderDao.getAllReminder()
}