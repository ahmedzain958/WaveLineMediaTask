package com.wavelinemedia.workmanager.domain.use_cases

import com.wavelinemedia.workmanager.domain.repository.QuoteRepository
import javax.inject.Inject

class SetupPeriodicWorkRequestUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    operator fun invoke() = quoteRepository.setupPeriodicWorkRequest()
}