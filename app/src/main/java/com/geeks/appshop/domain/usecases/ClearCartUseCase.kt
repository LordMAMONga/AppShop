package com.geeks.appshop.domain.usecases

import com.geeks.appshop.domain.repository.CartRepository

class ClearCartUseCase (
    private val repository: CartRepository
) {
    suspend operator fun invoke() {
        return repository.clearCart()
    }
}