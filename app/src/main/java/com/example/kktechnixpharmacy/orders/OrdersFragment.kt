package com.example.kktechnixpharmacy.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kktechnixpharmacy.R
import com.example.kktechnixpharmacy.databinding.FragmentOrdersBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class OrdersFragment : Fragment() {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orders = mutableListOf(
            OrderedProductData("Waiting for Payment","02-12-22","ABC123", "Home", 101),
            OrderedProductData("Failed","03-12-22","XYZ123", "Home2", 102),
            OrderedProductData("Delivered","04-12-22","123ABC", "Home3", 103),
            OrderedProductData("Ongoing","05-12-22","123XYZ", "Home4", 104)
        )

        binding.rvOrdersList.layoutManager = LinearLayoutManager(requireContext())

        val ordersRecyclerAdapter = RecyclerViewAdapterOrders(orders)

        binding.rvOrdersList.adapter = ordersRecyclerAdapter

        binding.tbOrdersFilter.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> { // All
                        ordersRecyclerAdapter.updateList(orders)
                    }
                    1 -> { // Ongoing
                        val ordersOngoing: List<OrderedProductData> = orders.filter {
                            it.status == resources.getString(R.string.ongoing)
                        }
                        ordersRecyclerAdapter.updateList(ordersOngoing)
                    }
                    2 -> { // Delivered
                        val ordersOnDelivered: List<OrderedProductData> = orders.filter {
                            it.status == resources.getString(R.string.delivered)
                        }
                        ordersRecyclerAdapter.updateList(ordersOnDelivered)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                return
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                return
            }

        })

        binding.fbGoToPaymentPage.setOnClickListener {
            val action = OrdersFragmentDirections.actionOrdersFragmentToPaymentFragment()
            view.findNavController().navigate(action)
        }
    }
}