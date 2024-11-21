package com.wavelinemedia.shopper_fk.data.repository

import com.wavelinemedia.shopper_fk.domain.model.CategoriesListModel
import com.wavelinemedia.shopper_fk.domain.network.NetworkService
import com.wavelinemedia.shopper_fk.domain.network.ResultWrapper
import com.wavelinemedia.shopper_fk.domain.repository.CategoryRepository


class CategoryRepositoryImpl(val networkService: NetworkService) : CategoryRepository {
    override suspend fun getCategories(): ResultWrapper<CategoriesListModel> {
        return networkService.getCategories()
    }
}