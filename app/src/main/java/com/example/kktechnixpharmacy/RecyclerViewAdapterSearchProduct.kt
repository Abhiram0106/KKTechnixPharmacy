package com.example.kktechnixpharmacy

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.example.kktechnixpharmacy.databinding.SearchProductListItemBinding

class RecyclerViewAdapterSearchProduct (
    private var searchPageProductList: MutableList<SearchPageProductData>,
    private var onProductClicked: ((position : Int) -> Unit)
        ) : RecyclerView.Adapter<RecyclerViewAdapterSearchProduct.ViewHolderSearchProduct>() {


    val initialList = ArrayList<SearchPageProductData>().apply {
        addAll(searchPageProductList)
    }


    inner class ViewHolderSearchProduct(private val binding: SearchProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: SearchPageProductData) = binding.apply {
            tvProductNameInSearchPage.text = product.productName
            tvProductRatingInSearchPage.text = product.rating.toString()
            when{
                product.rating < 1.5 -> cvRating.setBackgroundColor(itemView.resources.getColor(R.color.rating_red,null))
                product.rating < 3.0 -> cvRating.setBackgroundColor(itemView.resources.getColor(R.color.rating_yellow,null))
                product.rating >= 3.0 -> cvRating.setBackgroundColor(itemView.resources.getColor(R.color.rating_green,null))
                else -> cvRating.setBackgroundColor(itemView.resources.getColor(R.color.rating_red,null))
            }
            tvProductQuantityInSearchPage.text = product.productQuantity
            tvProductIngredientInSearchPage.text = product.ingredient
            tvProductPriceInSearchPage.text = itemView.context.getString(R.string.price, product.price)
            tvDeliveryTimeInSearchPage.text = itemView.context.getString(
                R.string.time_and_distance,
                product.deliveryTime,
                product.storeDistance
            )
            tvStoreNameInSearchPage.text = product.storeName

            ivProductImageInSearchPage.setImageResource(R.drawable.picture_tablet)

            root.setOnClickListener {
                onProductClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSearchProduct {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SearchProductListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolderSearchProduct(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderSearchProduct, position: Int) {
        holder.bind(searchPageProductList[position])
    }

    override fun getItemCount(): Int {
        return searchPageProductList.size
    }

    fun getFilter() : Filter {
        return medsFilter
    }

    private val medsFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<SearchPageProductData> = ArrayList()
            if (constraint.isNullOrEmpty()) {
//                TODO if search is empty, display recent search items
                initialList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString()
                initialList.forEach {
                    if (it.productName.contains(query, ignoreCase = true) || it.storeName.contains(
                            query,
                            ignoreCase = true
                        )
                    ) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is MutableList<*>) {
                searchPageProductList.clear()
                searchPageProductList.addAll(results.values as MutableList<SearchPageProductData>)
                searchPageProductList.sortBy {
                    it.price
                }
                notifyDataSetChanged()
            }
        }
    }
}