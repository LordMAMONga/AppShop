package com.geeks.appshop.domain.usecases

import com.geeks.appshop.domain.model.CartItem
import com.geeks.appshop.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CheckoutUseCase (
    private val repository: CartRepository
) {
    suspend operator fun invoke(): Result<String> {
        return repository.checkout()
    }
}