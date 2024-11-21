package com.wavelinemedia.shopper_fk.domain.model

data class CartModel (
    val data: List<CartItemModel>,
    val msg: String
)