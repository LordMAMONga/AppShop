package com.geeks.appshop.domain.usecases

import com.geeks.appshop.domain.model.Product
import com.geeks.appshop.domain.repository.CartRepository

class AddToCartUseCase(
    private val repository: CartRepository
) {
    suspend operator fun invoke(product: Product) {
        return repository.addToCart(product)
    }
}