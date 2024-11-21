package com.wavelinemedia.shopper_fk.data.model.response

import com.wavelinemedia.shopper_fk.data.model.DataProductModel
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    val `data`: List<DataProductModel>,
    val msg: String
) {
    fun toProductList() = com.wavelinemedia.shopper_fk.domain.model.ProductListModel(
        products = `data`.map { it.toProduct() },
        msg = msg
    )
}