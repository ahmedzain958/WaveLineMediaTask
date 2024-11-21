package com.wavelinemedia.shopper_fk.domain.network

import com.wavelinemedia.shopper_fk.domain.model.CartItemModel
import com.wavelinemedia.shopper_fk.domain.model.CartModel
import com.wavelinemedia.shopper_fk.domain.model.CartSummary
import com.wavelinemedia.shopper_fk.domain.model.CategoriesListModel
import com.wavelinemedia.shopper_fk.domain.model.ProductListModel
import com.wavelinemedia.shopper_fk.domain.model.request.AddCartRequestModel

interface NetworkService {
    suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>

    suspend fun addProductToCart(
        request: AddCartRequestModel
    ): ResultWrapper<CartModel>

    suspend fun getCart(): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel>
    suspend fun deleteItem(cartItemId: Int, userId: Int): ResultWrapper<CartModel>
    suspend fun getCartSummary(userId: Int): ResultWrapper<CartSummary>
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}