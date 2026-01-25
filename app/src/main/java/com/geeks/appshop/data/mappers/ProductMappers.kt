package com.geeks.appshop.data.mappers

import com.geeks.appshop.data.model.ProductDto
import com.geeks.appshop.data.model.RatingDto
import com.geeks.appshop.domain.model.Product
import com.geeks.appshop.domain.model.Rating

fun ProductDto.toDomain(): Product {
    return Product(
        id = this.id ?: -1,
        title = this.title ?: "",
        price = this.price ?: 0.0,
        description = this.description ?: "",
        category = this.category ?: "",
        image = this.image ?: "",
        rating = Rating(
            this.rating?.rate ?: 0.0,
            this.rating?.count ?: 0
        )
    )
}