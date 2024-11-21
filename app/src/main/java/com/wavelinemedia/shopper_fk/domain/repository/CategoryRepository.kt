package com.wavelinemedia.shopper_fk.domain.repository

import com.wavelinemedia.shopper_fk.domain.network.ResultWrapper
import com.wavelinemedia.shopper_fk.domain.model.CategoriesListModel

interface CategoryRepository {
     suspend fun getCategories(): ResultWrapper<CategoriesListModel>
}