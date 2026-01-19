package com.geeks.appshop.ui.di

import com.geeks.appshop.ui.fragments.product.ListViewModel
import com.geeks.appshop.ui.fragments.product.detail.DetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {

    viewModel { ListViewModel(get()) }
    viewModelOf(::DetailViewModel)
}