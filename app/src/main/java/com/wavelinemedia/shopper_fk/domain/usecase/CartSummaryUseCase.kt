package com.wavelinemedia.shopper_fk.domain.usecase

import com.wavelinemedia.shopper_fk.domain.repository.CartRepository

class CartSummaryUseCase (private val repository: CartRepository) {
    suspend fun execute(userId: Int) = repository.getCartSummary(userId)
}