package com.example.kktechnixpharmacy

data class SearchPageProductData(
    val productName: String,
    val productQuantity: String,
    val ingredient: String,
    val price: Int?,
    val deliveryTime: Int,
    val storeDistance: Int,
    val storeName: String,
    val rating: Double,
    val img: Int
) {
}