package com.example.kktechnixpharmacy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.example.kktechnixpharmacy.databinding.SearchItemBinding
import com.example.kktechnixpharmacy.databinding.SearchItemStoreBinding
import com.example.kktechnixpharmacy.databinding.SearchRecyclerviewTitleBinding

class RecyclerViewAdapterSearchProduct : RecyclerView.Adapter<ViewHolderSearchPageRecycler>() {

    var productItems = listOf<SearchRecyclerViewItem.Product>()
        set(value) {
            field = value
        }

    var storeItems = listOf<SearchRecyclerViewItem.Store>()
        set(value) {
            field = value
        }

    var titleItems = listOf<SearchRecyclerViewItem.Title>()
        set(value) {
            field = value
        }

    var recentSearchItems = mutableListOf<SearchRecyclerViewItem>()
        set(value) {
            field = value
        }

    var recentItemsUntouched = mutableListOf<SearchRecyclerViewItem>()
        set(value) {
            field = value
        }

    var savedRecentItems = mutableListOf<SearchRecyclerViewItem>().apply {
        add(SearchRecyclerViewItem.Title(1, "Past Searches"))
        addAll(recentItemsUntouched)

    }

//    var items = mutableListOf<SearchRecyclerViewItem>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    var itemClickListener : ( (view: View, item: SearchRecyclerViewItem, position: Int) -> Unit )? = null

    val initialList = ArrayList<SearchRecyclerViewItem>().apply {
        addAll(recentSearchItems)
    }

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
            is ViewHolderSearchPageRecycler.ViewHolderTitle -> holder.bind(recentSearchItems[position] as SearchRecyclerViewItem.Title)
            is ViewHolderSearchPageRecycler.ViewHolderStore -> holder.bind(recentSearchItems[position] as SearchRecyclerViewItem.Store)
            is ViewHolderSearchPageRecycler.ViewHolderProduct -> holder.bind(recentSearchItems[position] as SearchRecyclerViewItem.Product)
        }
    }

    override fun getItemCount(): Int {
        return recentSearchItems.size
    }


    override fun getItemViewType(position: Int): Int {
        return when(recentSearchItems[position]){
            is SearchRecyclerViewItem.Product -> R.layout.search_item
            is SearchRecyclerViewItem.Store -> R.layout.search_item_store
            is SearchRecyclerViewItem.Title -> R.layout.search_recyclerview_title
        }
    }


    fun getFilter() : Filter {
        return medsFilter
    }

    private val medsFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<SearchRecyclerViewItem> = ArrayList()


            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(initialList)
            } else {
                val query = constraint.toString()
                productItems.forEach {
                    if (it.name.contains(query, ignoreCase = true)) {
                        filteredList.add(it)
                    }
                }
                storeItems.forEach {
                    if (it.name.contains(query, ignoreCase = true)) {
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
                recentSearchItems.clear()
                recentSearchItems.addAll(savedRecentItems)
                recentSearchItems.add(titleItems[1])
                recentSearchItems.addAll(results.values as MutableList<SearchRecyclerViewItem>)

                notifyDataSetChanged()
            }
        }
    }
}