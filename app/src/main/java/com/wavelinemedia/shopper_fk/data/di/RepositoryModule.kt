package com.wavelinemedia.shopper_fk.data.di

import com.wavelinemedia.shopper_fk.data.repository.CartRepositoryImpl
import com.wavelinemedia.shopper_fk.data.repository.CategoryRepositoryImpl
import com.wavelinemedia.shopper_fk.data.repository.ProductRepositoryImpl
import com.wavelinemedia.shopper_fk.domain.repository.CartRepository
import com.wavelinemedia.shopper_fk.domain.repository.CategoryRepository
import com.wavelinemedia.shopper_fk.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get()) }
//    single<OrderRepository> { OrderRepositoryImpl(get()) }
}