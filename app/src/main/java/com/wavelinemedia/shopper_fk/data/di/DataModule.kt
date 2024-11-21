package com.wavelinemedia.shopper_fk.data.di

import com.wavelinemedia.shopper_fk.data.di.networkModule
import org.koin.dsl.module

val dataModule = module {
    includes(
        networkModule,
        repositoryModule
    )
}