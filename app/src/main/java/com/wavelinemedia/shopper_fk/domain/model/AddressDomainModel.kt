package com.wavelinemedia.shopper_fk.domain.model

data class AddressDomainModel(
    val addressLine: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String
)
