package com.wavelinemedia.shopper_fk.data.repository

import com.wavelinemedia.shopper_fk.domain.model.AddressDomainModel
import com.wavelinemedia.shopper_fk.domain.model.OrdersListModel
import com.wavelinemedia.shopper_fk.domain.network.NetworkService
import com.wavelinemedia.shopper_fk.domain.network.ResultWrapper
import com.wavelinemedia.shopper_fk.domain.repository.OrderRepository
/*

class OrderRepositoryImpl(private val networkService: NetworkService) : OrderRepository {
    override suspend fun placeOrder(addressDomainModel: AddressDomainModel): ResultWrapper<Long> {
        return networkService.placeOrder(addressDomainModel, 1)
    }

    override suspend fun getOrderList(): ResultWrapper<OrdersListModel> {
        return networkService.getOrderList()
    }
}*/
