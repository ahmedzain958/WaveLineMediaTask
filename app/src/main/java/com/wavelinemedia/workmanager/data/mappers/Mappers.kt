package com.wavelinemedia.workmanager.data.mappers

import com.wavelinemedia.workmanager.data.model.QuoteDTO
import com.wavelinemedia.workmanager.domain.model.Quote

fun QuoteDTO.toDomain(workType: String): Quote {
    return Quote(author, id, quote, workType)
}