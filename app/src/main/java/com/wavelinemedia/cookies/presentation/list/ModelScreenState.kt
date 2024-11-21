package com.wavelinemedia.cookies.presentation.list

import com.wavelinemedia.cookies.domain.Model

data class ModelScreenState(
    val modelsList: List<Model>,
    val isLoading: Boolean,
    val error: String? = null,
)
