package com.example.kktechnixpharmacy.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.kktechnixpharmacy.databinding.FragmentNavigationDrawerBinding

class NavigationDrawerFragment : Fragment() {

    private var _binding: FragmentNavigationDrawerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationDrawerBinding.inflate(inflater, container, false)
        val view = binding.root
        return (view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvUserName.text = "Insert Name Here"
        binding.tvUserPhoneNo.text = "+91-123456789"
        binding.tvUserEmail.text = "insertemail@email.com"

        binding.sivCloseNavDrawer.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}