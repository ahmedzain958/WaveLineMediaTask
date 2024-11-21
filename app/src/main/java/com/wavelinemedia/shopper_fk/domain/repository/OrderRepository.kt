package com.wavelinemedia.shopper_fk.domain.repository

import com.wavelinemedia.shopper_fk.domain.model.AddressDomainModel
import com.wavelinemedia.shopper_fk.domain.model.OrdersListModel
import com.wavelinemedia.shopper_fk.domain.network.ResultWrapper

interface OrderRepository {
    suspend fun placeOrder(addressDomainModel: AddressDomainModel): ResultWrapper<Long>
    suspend fun getOrderList(): ResultWrapper<OrdersListModel>
}