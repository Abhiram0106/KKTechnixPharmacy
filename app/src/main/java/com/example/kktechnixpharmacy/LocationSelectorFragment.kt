package com.example.kktechnixpharmacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kktechnixpharmacy.databinding.FragmentLocationSelectorBinding

class LocationSelectorFragment : Fragment() {

    var _binding: FragmentLocationSelectorBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationSelectorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addressList = listOf(
            PinnedAddressesData(R.drawable.ic_baseline_home_24,"Home","541, Vasant Vihar, Dehradun"),
            PinnedAddressesData(R.drawable.ic_baseline_work_24,"Work","Dehradun, 541, Vasant Vihar")
        )

        binding.rvPinnedLoc.layoutManager = LinearLayoutManager(view.context)

        val addressRecyclerAdapter = RecyclerViewAdapterPinnedAddresses(addressList)

        binding.rvPinnedLoc.adapter = addressRecyclerAdapter

        binding.ivDownArrow.setOnClickListener {
            view.findNavController().popBackStack()
        }
        binding.tvSelectALoc.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}