package com.wavelinemedia.shopper_fk.domain.usecase

import com.wavelinemedia.shopper_fk.domain.repository.CategoryRepository

class GetCategoriesUseCase (private val repository: CategoryRepository) {
    suspend fun execute() = repository.getCategories()
}