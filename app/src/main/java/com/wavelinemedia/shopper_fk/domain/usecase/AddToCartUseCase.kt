package com.wavelinemedia.shopper_fk.domain.usecase

import com.wavelinemedia.shopper_fk.domain.model.request.AddCartRequestModel
import com.wavelinemedia.shopper_fk.domain.repository.CartRepository

class AddToCartUseCase(private val cartRepository: CartRepository) {
    suspend fun execute(request: AddCartRequestModel)
    = cartRepository.addProductToCart(request)
}