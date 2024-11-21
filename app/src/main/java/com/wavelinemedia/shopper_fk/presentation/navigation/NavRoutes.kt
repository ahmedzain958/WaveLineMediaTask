package com.wavelinemedia.shopper_fk.presentation.navigation

import com.wavelinemedia.shopper_fk.presentation.model.UiProductModel
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object CartScreen

@Serializable
object ProfileScreen

@Serializable
object CartSummaryScreen

@Serializable
data class ProductDetailsRouteScreen(val product: UiProductModel)