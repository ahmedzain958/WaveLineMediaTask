package com.wavelinemedia.medicationreminderalarmbroadcast.domain.use_cases

import com.wavelinemedia.medicationreminderalarmbroadcast.domain.repository.ReminderRepository
import javax.inject.Inject

class GetAllReminderUseCase @Inject constructor(private val reminderRepository: ReminderRepository) {

    operator fun invoke() = reminderRepository.getAllReminders()

}