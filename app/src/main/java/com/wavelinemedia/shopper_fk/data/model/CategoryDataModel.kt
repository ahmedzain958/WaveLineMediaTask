package com.wavelinemedia.shopper_fk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDataModel(
    val id: Int,
    val image: String,
    val title: String
) {
    fun toCategory() = com.wavelinemedia.shopper_fk.domain.model.Category(
        id = id,
        image = image,
        title = title
    )
}