package com.wavelinemedia.workmanager.domain.repository

import com.wavelinemedia.workmanager.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuote()

    fun getAllQuotes(): Flow<List<Quote>>

    fun setupPeriodicWorkRequest()


}