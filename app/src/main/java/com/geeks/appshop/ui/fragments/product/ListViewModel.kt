package com.geeks.appshop.ui.fragments.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.appshop.domain.model.Product
import com.geeks.appshop.domain.usecases.AddToCartUseCase
import com.geeks.appshop.domain.usecases.GetProductsUseCase
import com.geeks.appshop.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val addToCartUseCase: AddToCartUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Product>>> = _state.asStateFlow()


    init {
        loadProducts()
    }


    fun loadProducts() {
        viewModelScope.launch {

            _state.value = UiState.Loading
            try {
                val products = getProductsUseCase()
                _state.value = UiState.Success(products)

            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error")
            }
        }
    }
    fun addToCart(product: Product) {
        viewModelScope.launch {
            addToCartUseCase(product)
        }
    }
}