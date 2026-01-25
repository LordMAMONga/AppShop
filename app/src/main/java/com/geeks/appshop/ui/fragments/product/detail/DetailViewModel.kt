package com.geeks.appshop.ui.fragments.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.appshop.domain.model.Product
import com.geeks.appshop.domain.usecases.GetProductByIdUseCase
import com.geeks.appshop.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<Product>>(UiState.Loading)
    val state: StateFlow<UiState<Product>> = _state.asStateFlow()

    fun loadProducts(id: Int) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                _state.value = UiState.Success(getProductByIdUseCase(id))
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error")
            }
        }
    }
}