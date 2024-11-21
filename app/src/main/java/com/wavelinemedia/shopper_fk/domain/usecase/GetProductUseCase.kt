package com.wavelinemedia.shopper_fk.domain.usecase

import com.wavelinemedia.shopper_fk.domain.repository.ProductRepository

class GetProductUseCase(private val repository: ProductRepository) {
    suspend fun execute(category:Int?) = repository.getProducts(category)
}