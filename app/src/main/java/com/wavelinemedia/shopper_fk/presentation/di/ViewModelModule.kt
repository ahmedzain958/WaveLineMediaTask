package com.wavelinemedia.shopper_fk.presentation.di

import com.wavelinemedia.shopper_fk.presentation.ui.feature.cart.CartViewModel
import com.wavelinemedia.shopper_fk.presentation.ui.feature.home.HomeViewModel
import com.wavelinemedia.shopper_fk.presentation.ui.feature.product_details.ProductDetailsViewModel
import com.wavelinemedia.shopper_fk.presentation.ui.feature.summary.CartSummaryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        ProductDetailsViewModel(get())
    }
    viewModel {
        CartViewModel(get(), get(), get())
    }
    viewModel {
        CartSummaryViewModel(get())
    }
}