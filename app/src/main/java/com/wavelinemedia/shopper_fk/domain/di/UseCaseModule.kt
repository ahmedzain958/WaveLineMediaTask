package com.wavelinemedia.shopper_fk.domain.di

import com.wavelinemedia.shopper_fk.domain.usecase.AddToCartUseCase
import com.wavelinemedia.shopper_fk.domain.usecase.CartSummaryUseCase
import com.wavelinemedia.shopper_fk.domain.usecase.DeleteProductUseCase
import com.wavelinemedia.shopper_fk.domain.usecase.GetCartUseCase
import com.wavelinemedia.shopper_fk.domain.usecase.GetCategoriesUseCase
import com.wavelinemedia.shopper_fk.domain.usecase.GetProductUseCase
import com.wavelinemedia.shopper_fk.domain.usecase.OrderListUseCase
import com.wavelinemedia.shopper_fk.domain.usecase.PlaceOrderUseCase
import com.wavelinemedia.shopper_fk.domain.usecase.UpdateQuantityUseCase
import org.koin.dsl.module


val useCaseModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { AddToCartUseCase(get()) }
    factory { GetCartUseCase(get()) }
    factory { UpdateQuantityUseCase(get()) }
    factory { DeleteProductUseCase(get()) }
    factory { CartSummaryUseCase(get()) }
    factory { PlaceOrderUseCase(get()) }
    factory { OrderListUseCase(get()) }
}