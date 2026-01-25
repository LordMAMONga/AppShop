package com.geeks.appshop.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartResponseDto(
    @SerialName("id") val id: Int
)