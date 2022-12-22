package com.example.kktechnixpharmacy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.INVISIBLE
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kktechnixpharmacy.databinding.FragmentSearchProductBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

class SearchProductFragment : Fragment() {

    private var isAntibioticChecked = false
    private var isAyurvedaChecked = false
    private var isHygieneChecked = false

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
            SearchPageProductData("mask","pack of 10", "cotton", 500, 50, 20, "ABC Pharmacy", 1.0),
            SearchPageProductData("mask","pack of 9", "silk", 400, 40, 30, "XYZ Pharmacy", 2.0),
            SearchPageProductData("mask","pack of 8", "jute", 300, 30, 40, "123 Pharmacy", 3.0),
            SearchPageProductData("mask","pack of 7", "wool", 200, 20, 50, "789 Pharmacy", 4.0),
            SearchPageProductData("med1","10 mg", "ing1", 100, 10, 10, "qwe Pharmacy", 5.0),
            SearchPageProductData("med2","20 mg", "ing2", 200, 20, 20, "iop Pharmacy", 1.0),
            SearchPageProductData("med3","30 mg", "ing3", 300, 30, 30, "asd Pharmacy", 2.0),
            SearchPageProductData("med4","40 mg", "ing4", 400, 40, 40, "jkl Pharmacy", 3.0),
            )

        binding.rvSearchProductList.layoutManager = GridLayoutManager(view.context,1, RecyclerView.VERTICAL, false)
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