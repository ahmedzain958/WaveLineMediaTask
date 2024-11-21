package com.wavelinemedia.shopper_fk.domain.usecase

import com.wavelinemedia.shopper_fk.domain.model.AddressDomainModel
import com.wavelinemedia.shopper_fk.domain.repository.OrderRepository

class PlaceOrderUseCase(val orderRepository: OrderRepository) {
    suspend fun execute(addressDomainModel: AddressDomainModel) =
        orderRepository.placeOrder(addressDomainModel)
}