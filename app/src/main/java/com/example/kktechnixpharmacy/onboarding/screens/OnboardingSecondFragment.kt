package com.example.kktechnixpharmacy.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kktechnixpharmacy.R
import com.example.kktechnixpharmacy.databinding.FragmentOnboardingSecondBinding
import com.example.kktechnixpharmacy.onboarding.ViewPagerFragmentDirections

class OnboardingSecondFragment : Fragment(R.layout.fragment_onboarding_second) {

    private var _binding: FragmentOnboardingSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingSecondBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSkip.setOnClickListener {
            val action = ViewPagerFragmentDirections.actionViewPagerFragmentToCartFragment()
            view.findNavController().navigate(action)
        }

        val viewPager = activity?.findViewById<ViewPager2>(R.id.vp_ViewPager)

        binding.btnNext.setOnClickListener {
            viewPager?.currentItem = 2
        }
        binding.btnBack.setOnClickListener {
            viewPager?.currentItem = 0
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =  null
    }
}