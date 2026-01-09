package com.geeks.appshop.data.repository

import com.geeks.appshop.data.datasourse.StoreApi
import com.geeks.appshop.data.mappers.toDomain
import com.geeks.appshop.domain.model.Product
import com.geeks.appshop.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val storeApi: StoreApi
) : ProductRepository {
    override suspend fun getProducts(): List<Product> =
        storeApi.getAllProducts().map { productDto ->
            productDto.toDomain()
        }

    override suspend fun getProductById(productId: Int): Product = storeApi.getProductById(productId).toDomain()
}