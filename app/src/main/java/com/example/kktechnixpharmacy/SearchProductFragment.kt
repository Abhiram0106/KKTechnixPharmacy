package com.example.kktechnixpharmacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kktechnixpharmacy.databinding.FragmentSearchProductBinding
import com.google.android.material.snackbar.Snackbar

class SearchProductFragment : Fragment() {

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

        val searchProductList = mutableListOf(
            SearchPageProductData("mask","pack of 10", "cotton", 500, 50, 20, "ABC Pharmacy"),
            SearchPageProductData("mask","pack of 9", "silk", 400, 40, 30, "XYZ Pharmacy"),
            SearchPageProductData("mask","pack of 8", "jute", 300, 30, 40, "123 Pharmacy"),
            SearchPageProductData("mask","pack of 7", "wool", 200, 20, 50, "789 Pharmacy"),
            SearchPageProductData("med1","10 mg", "ing1", 100, 10, 10, "qwe Pharmacy"),
            SearchPageProductData("med2","20 mg", "ing2", 200, 20, 20, "iop Pharmacy"),
            SearchPageProductData("med3","30 mg", "ing3", 300, 30, 30, "asd Pharmacy"),
            SearchPageProductData("med4","40 mg", "ing4", 400, 40, 40, "jkl Pharmacy"),
            )

        binding.rvSearchProductList.layoutManager = GridLayoutManager(view.context,2, RecyclerView.VERTICAL, false)
        val searchProductListAdapter = RecyclerViewAdapterSearchProduct(
            searchPageProductList =  searchProductList,
            onProductClicked = { position ->
                Snackbar.make(view, "Click pos=$position",Snackbar.LENGTH_SHORT).show()
            }
        )

        binding.rvSearchProductList.adapter = searchProductListAdapter


        binding.svRecyclerViewSearch.queryHint = getString(R.string.search_hint)

        binding.svRecyclerViewSearch.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchProductListAdapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchProductListAdapter.getFilter().filter(newText)
                return true
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}