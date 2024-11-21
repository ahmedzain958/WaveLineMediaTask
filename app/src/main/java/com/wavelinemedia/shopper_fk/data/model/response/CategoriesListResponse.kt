package com.wavelinemedia.shopper_fk.data.model.response

import com.wavelinemedia.shopper_fk.data.model.CategoryDataModel
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesListResponse(
    val `data`: List<CategoryDataModel>,
    val msg: String
) {
    fun toCategoriesList() = com.wavelinemedia.shopper_fk.domain.model.CategoriesListModel(
        categories = `data`.map { it.toCategory() },
        msg = msg
    )
}