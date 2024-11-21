package com.wavelinemedia.shopper_fk.domain.repository

import com.wavelinemedia.shopper_fk.domain.model.CartItemModel
import com.wavelinemedia.shopper_fk.domain.model.CartModel
import com.wavelinemedia.shopper_fk.domain.model.CartSummary
import com.wavelinemedia.shopper_fk.domain.model.request.AddCartRequestModel
import com.wavelinemedia.shopper_fk.domain.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(
        request: AddCartRequestModel
    ): ResultWrapper<CartModel>

    suspend fun getCart(): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel>
    suspend fun deleteItem(cartItemId: Int, userId: Int): ResultWrapper<CartModel>
    suspend fun getCartSummary(userId: Int): ResultWrapper<CartSummary>
}