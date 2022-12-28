package com.example.kktechnixpharmacy.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.kktechnixpharmacy.databinding.FragmentMockHomeBinding

class MockHomeFragment : Fragment() {

    var _binding: FragmentMockHomeBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMockHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvOpenLocSelector.setOnClickListener {
            val directions = MockHomeFragmentDirections.actionMockHomeFragmentToLocationSelectorFragment()
            view.findNavController().navigate(directions)
        }

        binding.ivUserProfilePicture.setOnClickListener {
            val directions = MockHomeFragmentDirections.actionMockHomeFragmentToNavigationDrawerFragment()
            view.findNavController().navigate(directions)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}