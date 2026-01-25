package com.geeks.appshop.data.repository

import com.geeks.appshop.data.datasourse.StoreApi
import com.geeks.appshop.data.mappers.toDto
import com.geeks.appshop.data.model.CartProductDto
import com.geeks.appshop.data.model.CartRequestDto
import com.geeks.appshop.domain.model.CartItem
import com.geeks.appshop.domain.model.Product
import com.geeks.appshop.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CartRepositoryImpl(
    private val api: StoreApi,
) : CartRepository {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    override val cartItems = _cartItems.asStateFlow()

    override suspend fun addToCart(product: Product) {
        _cartItems.update { currentList ->
            val existing = currentList.find { findProduct ->
                findProduct.product.id == product.id
            }
            if (existing == null) {
                return@update currentList + CartItem(product, 1)
            }
            currentList.map { currentProduct ->
                if (currentProduct.product.id == product.id) currentProduct.copy(quantity = currentProduct.quantity + 1)
                else currentProduct
            }

        }
    }

    override suspend fun clearCart() {
        _cartItems.value = emptyList()
    }

    override suspend fun checkout(): Result<String> {
        return try {
            val response = api.checkout(
                CartRequestDto(
                    products =
                        _cartItems.value.map { it.toDto() }
                ))

            Result.success("Заказ №${response.id} оформлен!")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}