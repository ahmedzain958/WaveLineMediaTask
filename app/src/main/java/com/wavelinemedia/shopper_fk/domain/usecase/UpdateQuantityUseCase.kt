package com.wavelinemedia.shopper_fk.domain.usecase

import com.wavelinemedia.shopper_fk.domain.model.CartItemModel
import com.wavelinemedia.shopper_fk.domain.repository.CartRepository

class UpdateQuantityUseCase(private val cartRepository: CartRepository) {
    suspend fun execute(cartItemModel: CartItemModel) = cartRepository.updateQuantity(cartItemModel)
}