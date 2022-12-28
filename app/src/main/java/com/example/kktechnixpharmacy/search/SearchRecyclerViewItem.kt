package com.example.kktechnixpharmacy.search

sealed class SearchRecyclerViewItem {

    data class Title(
        val id: Int,
        val title: String
    ) : SearchRecyclerViewItem()

    data class Product(
        val img: Int,
        val name: String,
        val price: Int,
    ) : SearchRecyclerViewItem()

    data class Store(
        val img: Int,
        val name: String,
        val desc: String
    ) : SearchRecyclerViewItem()
}
