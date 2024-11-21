package com.wavelinemedia.shopper_fk.data.model.response

import com.wavelinemedia.shopper_fk.domain.model.OrdersData
import kotlinx.serialization.Serializable

@Serializable
data class OrderListData(
    val id: Int,
    val items: List<OrderItem>,
    val orderDate: String,
    val status: String,
    val totalAmount: Double,
    val userId: Int
) {
    fun toDomainResponse():  OrdersData {
        return OrdersData(
            id = id,
            items = items.map { it.toDomainResponse() },
            orderDate = orderDate,
            status = status,
            totalAmount = totalAmount,
            userId = userId
        )
    }
}