package com.wavelinemedia.shopper_fk.domain.di

import org.koin.dsl.module

val domainModule = module {
    includes(useCaseModule)
}