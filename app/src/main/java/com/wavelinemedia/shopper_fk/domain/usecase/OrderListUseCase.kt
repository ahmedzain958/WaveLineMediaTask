package com.wavelinemedia.shopper_fk.domain.usecase

import com.wavelinemedia.shopper_fk.domain.model.OrdersListModel
import com.wavelinemedia.shopper_fk.domain.repository.OrderRepository

class OrderListUseCase(
    private val repository: OrderRepository
) {
    suspend fun execute() = repository.getOrderList()
}