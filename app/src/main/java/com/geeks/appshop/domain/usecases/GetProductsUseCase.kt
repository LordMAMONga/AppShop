package com.geeks.appshop.domain.usecases

import com.geeks.appshop.domain.model.Product
import com.geeks.appshop.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository

) {
    suspend operator fun invoke(): List<Product> = repository.getProducts()
}