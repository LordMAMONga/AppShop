package com.geeks.appshop.domain.repository


import com.geeks.appshop.domain.model.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>
    suspend fun getProductById(productId: Int): Product
}