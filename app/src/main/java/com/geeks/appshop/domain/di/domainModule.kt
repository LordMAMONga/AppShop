package com.geeks.appshop.domain.di

import com.geeks.appshop.domain.usecases.GetProductByIdUseCase
import com.geeks.appshop.domain.usecases.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(get ()) }
    factory { GetProductByIdUseCase(get()) }
}