package com.wavelinemedia.shopper_fk.domain.usecase

import com.wavelinemedia.shopper_fk.domain.repository.CartRepository

class GetCartUseCase(val cartRepository: CartRepository) {
    suspend fun execute() = cartRepository.getCart()
}