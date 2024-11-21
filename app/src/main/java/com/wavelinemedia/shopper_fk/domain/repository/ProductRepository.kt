package com.wavelinemedia.shopper_fk.domain.repository

import com.wavelinemedia.shopper_fk.domain.model.ProductListModel
import com.wavelinemedia.shopper_fk.domain.network.ResultWrapper

interface ProductRepository {
    suspend fun getProducts(category:Int?): ResultWrapper<ProductListModel>
}