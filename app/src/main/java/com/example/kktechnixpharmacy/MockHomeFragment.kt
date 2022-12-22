package com.example.kktechnixpharmacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.kktechnixpharmacy.databinding.FragmentMockHomeBinding
import com.google.android.material.snackbar.Snackbar

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


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}