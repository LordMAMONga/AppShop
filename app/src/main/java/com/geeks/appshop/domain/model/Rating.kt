package com.geeks.appshop.domain.model


data class Rating(
    val rate: Double,
    val count: Int
){
    companion object{
        fun empty(): Rating{
            return Rating(
                rate = 0.0,
                count = 0
            )
        }
    }
}