package com.wavelinemedia.shopper_fk.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class OrdersListResponse(
    val `data`: List<OrderListData>,
    val msg: String
) {
    fun toDomainResponse(): com.wavelinemedia.shopper_fk.domain.model.OrdersListModel {
        return com.wavelinemedia.shopper_fk.domain.model.OrdersListModel(
            `data` = `data`.map { it.toDomainResponse() },
            msg = msg
        )
    }
}