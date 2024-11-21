package com.wavelinemedia.shopper_fk.data.model.request

import com.wavelinemedia.shopper_fk.domain.model.request.AddCartRequestModel
import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequest(
    val productId: Int,
    val quantity: Int
) {
    companion object {
        fun fromCartRequestModel(addCartRequestModel: AddCartRequestModel) = AddToCartRequest(
            productId = addCartRequestModel.productId,
            quantity = addCartRequestModel.quantity,
        )
    }
}