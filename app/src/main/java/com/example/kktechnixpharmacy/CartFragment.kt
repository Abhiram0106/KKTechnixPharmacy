package com.example.kktechnixpharmacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kktechnixpharmacy.databinding.FragmentCartBinding


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = mutableListOf(
            ProductData("Mask1","cotton1",101,1),
            ProductData("Mask2","cotton2",102,2),
            ProductData("Mask3","cotton3",103,3),
            ProductData("Mask4","cotton4",104,4),
            ProductData("Mask5","cotton5",105,5),
            ProductData("Mask6","cotton6",106,6)
        )

        binding.rvCartList.layoutManager = LinearLayoutManager(requireContext())

        val productsRecyclerAdapter = RecyclerViewAdapterCart(
            productsList = products,
            onDecrementClicked = { counter, position ->
                products[position].counter = counter-1
            },
            onIncrementClicked = { counter, position ->
                products[position].counter = counter+1
            }
            //    TODO swipe to delete
        )
        binding.rvCartList.adapter = productsRecyclerAdapter


        binding.fbGoToOrdersPage.setOnClickListener {
            val action = CartFragmentDirections.actionCartFragmentToOrdersFragment()
            view.findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}