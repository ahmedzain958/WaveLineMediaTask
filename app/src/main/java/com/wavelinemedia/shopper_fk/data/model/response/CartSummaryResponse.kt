package com.wavelinemedia.shopper_fk.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CartSummaryResponse(
    val `data`: Summary,
    val msg: String
) {
    fun toCartSummary() = com.wavelinemedia.shopper_fk.domain.model.CartSummary(
        data = `data`.toSummaryData(),
        msg = msg
    )
}