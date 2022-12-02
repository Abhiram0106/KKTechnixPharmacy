package com.example.kktechnixpharmacy

data class OrderedProductData(
    val status: String,
    val date: String,
    val transactionID: String,
    val address: String,
    val payment: Int
)