package com.geeks.appshop.domain.repository

import com.geeks.appshop.domain.model.CartItem
import com.geeks.appshop.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    val cartItems: Flow<List<CartItem>>

    suspend fun addToCart(product: Product)

    suspend fun checkout(): Result<String>

    suspend fun clearCart()
}