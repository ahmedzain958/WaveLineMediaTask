package com.wavelinemedia.shopper_fk.presentation.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}