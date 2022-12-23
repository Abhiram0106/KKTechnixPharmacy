package com.example.kktechnixpharmacy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kktechnixpharmacy.databinding.SearchItemBinding
import com.example.kktechnixpharmacy.databinding.SearchItemStoreBinding
import com.example.kktechnixpharmacy.databinding.SearchRecyclerviewTitleBinding

class RecyclerViewAdapterSearchProduct : RecyclerView.Adapter<ViewHolderSearchPageRecycler>() {

    var items = listOf<SearchRecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener : ( (view: View, item: SearchRecyclerViewItem, position: Int) -> Unit )? = null

//    val initialList = ArrayList<SearchRecyclerViewItem>().apply {
//        addAll(items)
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderSearchPageRecycler {
        return when(viewType) {

            R.layout.search_recyclerview_title -> ViewHolderSearchPageRecycler.ViewHolderTitle(
                SearchRecyclerviewTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.search_item -> ViewHolderSearchPageRecycler.ViewHolderProduct(
                SearchItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.search_item_store -> ViewHolderSearchPageRecycler.ViewHolderStore(
                SearchItemStoreBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw java.lang.IllegalArgumentException("Invalid ViewType at Search RV")
        }
    }

    override fun onBindViewHolder(holder: ViewHolderSearchPageRecycler, position: Int) {

        holder.itemClickListenerInViewHolder = itemClickListener

        when(holder){
            is ViewHolderSearchPageRecycler.ViewHolderTitle -> holder.bind(items[position] as SearchRecyclerViewItem.Title)
            is ViewHolderSearchPageRecycler.ViewHolderStore -> holder.bind(items[position] as SearchRecyclerViewItem.Store)
            is ViewHolderSearchPageRecycler.ViewHolderProduct -> holder.bind(items[position] as SearchRecyclerViewItem.Product)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is SearchRecyclerViewItem.Product -> R.layout.search_item
            is SearchRecyclerViewItem.Store -> R.layout.search_item_store
            is SearchRecyclerViewItem.Title -> R.layout.search_recyclerview_title
        }
    }


//    fun getFilter() : Filter {
//        return medsFilter
//    }
//
//    private val medsFilter = object : Filter() {
//        override fun performFiltering(constraint: CharSequence?): FilterResults {
//            val filteredList: ArrayList<SearchPageProductData> = ArrayList()
//            if (constraint.isNullOrEmpty()) {
//                initialList.let { filteredList.addAll(it) }
//            } else {
//                val query = constraint.toString()
//                initialList.forEach {
//                    if (it.productName.contains(query, ignoreCase = true) || it.storeName   .contains(
//                            query,
//                            ignoreCase = true
//                        )
//                    ) {
//                        filteredList.add(it)
//                    }
//                }
//            }
//            val results = FilterResults()
//            results.values = filteredList
//            return results
//        }
//
//        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//            if (results?.values is MutableList<*>) {
//                searchPageProductList.clear()
//                searchPageProductList.addAll(results.values as MutableList<SearchPageProductData>)
//                searchPageProductList.sortBy {
//                    it.price
//                }
//                notifyDataSetChanged()
//            }
//        }
//    }
}