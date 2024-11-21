package com.wavelinemedia.shopper_fk.domain.usecase

import com.wavelinemedia.shopper_fk.domain.model.CartItemModel
import com.wavelinemedia.shopper_fk.domain.repository.CartRepository

class DeleteProductUseCase(private val cartRepository: CartRepository) {
    suspend fun execute(cartItemId: Int, userId: Int) = cartRepository.deleteItem(cartItemId, userId)
}