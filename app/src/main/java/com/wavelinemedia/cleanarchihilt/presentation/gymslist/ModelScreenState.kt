package com.wavelinemedia.cleanarchihilt.presentation.gymslist

import com.wavelinemedia.cleanarchihilt.domain.Model

data class ModelScreenState(
    val modelsList: List<Model>,
    val isLoading: Boolean,
    val error: String? = null
)
