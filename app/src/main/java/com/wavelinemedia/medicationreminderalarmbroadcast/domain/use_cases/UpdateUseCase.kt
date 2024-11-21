package com.wavelinemedia.medicationreminderalarmbroadcast.domain.use_cases

import com.wavelinemedia.medicationreminderalarmbroadcast.domain.model.Reminder
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.repository.ReminderRepository
import javax.inject.Inject

class UpdateUseCase @Inject constructor(private val reminderRepository: ReminderRepository) {

    suspend operator fun invoke(reminder: Reminder) = reminderRepository.update(reminder)

}