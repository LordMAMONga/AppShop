package com.geeks.appshop.domain.usecases

import com.geeks.appshop.domain.model.Product
import com.geeks.appshop.domain.repository.ProductRepository

class GetProductByIdUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int): Product =
        repository.getProductById(productId = productId)

}