package com.geeks.appshop.data.datasourse


import com.geeks.appshop.data.model.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {

    @GET("products")
    suspend fun getAllProducts(): List<ProductDto>

    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

}