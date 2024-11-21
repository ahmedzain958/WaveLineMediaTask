package com.wavelinemedia.shopper_fk.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CartResponse(
    val data: List<CartItem>,
    val msg: String
) {
    fun toCartModel(): com.wavelinemedia.shopper_fk.domain.model.CartModel {
        return com.wavelinemedia.shopper_fk.domain.model.CartModel(
            data = data.map { it.toCartItemModel() },
            msg = msg
        )
    }
}