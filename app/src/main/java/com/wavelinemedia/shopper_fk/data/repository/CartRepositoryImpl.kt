package com.wavelinemedia.shopper_fk.data.repository

import com.wavelinemedia.shopper_fk.domain.model.CartItemModel
import com.wavelinemedia.shopper_fk.domain.model.CartModel
import com.wavelinemedia.shopper_fk.domain.model.CartSummary
import com.wavelinemedia.shopper_fk.domain.model.request.AddCartRequestModel
import com.wavelinemedia.shopper_fk.domain.network.NetworkService
import com.wavelinemedia.shopper_fk.domain.network.ResultWrapper
import com.wavelinemedia.shopper_fk.domain.repository.CartRepository

class CartRepositoryImpl(private val networkService: NetworkService) : CartRepository {
    override suspend fun addProductToCart(request: AddCartRequestModel): ResultWrapper<CartModel> {
        return networkService.addProductToCart(request)
    }

    override suspend fun getCart(): ResultWrapper<CartModel> {
        return networkService.getCart()
    }

    override suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel> {
        return networkService.updateQuantity(cartItemModel)
    }

    override suspend fun deleteItem(cartItemId: Int, userId: Int): ResultWrapper<CartModel> {
        return networkService.deleteItem(cartItemId, userId)
    }

    override suspend fun getCartSummary(userId: Int): ResultWrapper<CartSummary> {
        return networkService.getCartSummary(userId)
    }
}