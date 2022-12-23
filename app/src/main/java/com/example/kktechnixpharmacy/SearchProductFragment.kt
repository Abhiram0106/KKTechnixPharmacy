package com.example.kktechnixpharmacy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kktechnixpharmacy.databinding.FragmentSearchProductBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

class SearchProductFragment : Fragment() {

    private var isAntibioticChecked = false
    private var isAyurvedaChecked = false
    private var isHygieneChecked = false
    private var searchProductListAdapter = RecyclerViewAdapterSearchProduct()

    var _binding: FragmentSearchProductBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val productList = mutableListOf<SearchRecyclerViewItem.Product>()
        productList.apply {
            add(SearchRecyclerViewItem.Product(R.drawable.picture_tablet, "product1", 100))
            add(SearchRecyclerViewItem.Product(R.drawable.picture_tablet, "product2", 200))
            add(SearchRecyclerViewItem.Product(R.drawable.picture_tablet, "product3", 300))
            add(SearchRecyclerViewItem.Product(R.drawable.picture_tablet, "product4", 400))
            add(SearchRecyclerViewItem.Product(R.drawable.picture_tablet, "product5", 500))
        }

        val storeList = mutableListOf<SearchRecyclerViewItem.Store>()
        storeList.apply {
            add(SearchRecyclerViewItem.Store(R.drawable.ic_baseline_home_24, "store1","store1desc"))
            add(SearchRecyclerViewItem.Store(R.drawable.ic_baseline_home_24, "store2","store2desc"))
            add(SearchRecyclerViewItem.Store(R.drawable.ic_baseline_home_24, "store3","store3desc"))
            add(SearchRecyclerViewItem.Store(R.drawable.ic_baseline_home_24, "store4","store4desc"))
            add(SearchRecyclerViewItem.Store(R.drawable.ic_baseline_home_24, "store5","store5desc"))
        }

        val recentList = mutableListOf<SearchRecyclerViewItem>()
        recentList.apply {
            add(productList[2])
            add(storeList[2])
        }

        val fullList = mutableListOf<SearchRecyclerViewItem>()
        fullList.apply {
            addAll(productList)
            addAll(storeList)
        }

        val searchList = mutableListOf<SearchRecyclerViewItem>()
        searchList.apply {
//            TODO reimplement search
        }


        val recyclerList = mutableListOf<SearchRecyclerViewItem>()
        recyclerList.apply {
            add(SearchRecyclerViewItem.Title(1, binding.rvSearchProductList.context.getString(R.string.past_searches)))
            addAll(recentList)
            add(SearchRecyclerViewItem.Title(2, binding.rvSearchProductList.context.getString(R.string.search_results)))
            addAll(fullList)
        }



        searchProductListAdapter.items = recyclerList
        searchProductListAdapter.itemClickListener = { view, item, position ->

            val message = when(item) {
                is SearchRecyclerViewItem.Product -> item.name
                is SearchRecyclerViewItem.Store -> item.name
                is SearchRecyclerViewItem.Title -> item.title
            }

            Snackbar.make(view, "At $position, $message", Snackbar.LENGTH_SHORT).show()
        }

        binding.rvSearchProductList.apply {
            layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
            hasFixedSize()
            adapter = searchProductListAdapter
        }




        binding.svRecyclerViewSearch.queryHint = getString(R.string.search_hint)

//        binding.svRecyclerViewSearch.setOnQueryTextListener(object : OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                searchProductListAdapter.getFilter().filter(query)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                searchProductListAdapter.getFilter().filter(newText)
//                return true
//            }
//
//        })


        binding.fabGoToMockHomePage.setOnClickListener {
            val directions = SearchProductFragmentDirections.actionSearchProductFragmentToMockHomeFragment()
            view.findNavController().navigate(directions)
        }


        binding.cvCategoryAntibiotic.setOnClickListener {
            isAntibioticChecked = !isAntibioticChecked
            categorySetter()
        }

        binding.cvCategoryAyurveda.setOnClickListener {
            isAyurvedaChecked = !isAyurvedaChecked
            categorySetter()
        }
        binding.cvCategoryHygiene.setOnClickListener {
            isHygieneChecked = !isHygieneChecked
            categorySetter()
        }


    }

    private fun categorySetter() {
        Log.d("ischecked","antibio=$isAntibioticChecked, ayur=$isAyurvedaChecked, hyg=$isHygieneChecked")


        if(isAntibioticChecked) {
            Log.d("didAntiBioRun","YES")
            binding.cvCategoryAntibiotic.strokeWidth = 10
            binding.cvCategoryAntibiotic.strokeColor = resources.getColor(R.color.kktechnix, null)
        } else {
            binding.cvCategoryAntibiotic.strokeWidth = 0
            binding.cvCategoryAntibiotic.strokeColor = resources.getColor(R.color.transparent, null)
        }

        if(isAyurvedaChecked) {
            Log.d("didAyurRun", "YES")
            binding.cvCategoryAyurveda.strokeWidth = 10
            binding.cvCategoryAyurveda.strokeColor = resources.getColor(R.color.kktechnix, null)
        }else {
                binding.cvCategoryAyurveda.strokeWidth = 0
                binding.cvCategoryAyurveda.strokeColor = resources.getColor(R.color.transparent, null)
            }

        if(isHygieneChecked) {
            Log.d("didHygRun", "YES")
            binding.cvCategoryHygiene.strokeWidth = 10
            binding.cvCategoryHygiene.strokeColor = resources.getColor(R.color.kktechnix, null)
        }else {
                binding.cvCategoryHygiene.strokeWidth = 0
                binding.cvCategoryHygiene.strokeColor = resources.getColor(R.color.transparent, null)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}