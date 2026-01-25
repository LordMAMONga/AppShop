package com.geeks.appshop.domain.di

import com.geeks.appshop.domain.usecases.AddToCartUseCase
import com.geeks.appshop.domain.usecases.CheckoutUseCase
import com.geeks.appshop.domain.usecases.ClearCartUseCase
import com.geeks.appshop.domain.usecases.GetCartItemUseCase
import com.geeks.appshop.domain.usecases.GetProductByIdUseCase
import com.geeks.appshop.domain.usecases.GetProductsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(get ()) }
    factory { GetProductByIdUseCase(get()) }

    factoryOf(::AddToCartUseCase)
    factoryOf(::GetCartItemUseCase)
    factoryOf(::ClearCartUseCase)
    factoryOf(::CheckoutUseCase)
}