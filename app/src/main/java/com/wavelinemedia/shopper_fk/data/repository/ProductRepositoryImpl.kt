package com.wavelinemedia.shopper_fk.data.repository

import com.wavelinemedia.shopper_fk.domain.model.Product
import com.wavelinemedia.shopper_fk.domain.model.ProductListModel
import com.wavelinemedia.shopper_fk.domain.network.NetworkService
import com.wavelinemedia.shopper_fk.domain.network.ResultWrapper
import com.wavelinemedia.shopper_fk.domain.repository.ProductRepository

class ProductRepositoryImpl(private val networkService: NetworkService) : ProductRepository {
    override suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel> {
        return networkService.getProducts(category)
    }
}