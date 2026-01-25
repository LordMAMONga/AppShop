package com.geeks.appshop.data.mappers

import com.geeks.appshop.data.model.CartProductDto
import com.geeks.appshop.domain.model.CartItem

fun CartItem.toDto(): CartProductDto{

    return CartProductDto(
        productId = this.product.id,
        quantity = this.quantity
    )
}