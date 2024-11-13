package com.wavelinemedia.cleanarchihilt.domain

data class Model(
    val id: Int,
    val name: String,
    val place: String,
    val isOpen: Boolean,
    val isFavorite: Boolean = false
)
