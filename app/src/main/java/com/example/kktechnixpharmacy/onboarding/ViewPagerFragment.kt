package com.example.kktechnixpharmacy.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kktechnixpharmacy.databinding.FragmentViewpagerBinding
import com.example.kktechnixpharmacy.onboarding.screens.OnboardingFristFragment
import com.example.kktechnixpharmacy.onboarding.screens.OnboardingSecondFragment
import com.example.kktechnixpharmacy.onboarding.screens.OnboardingThirdFragment

class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewpagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewpagerBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = listOf<Fragment>(
            OnboardingFristFragment(),
            OnboardingSecondFragment(),
            OnboardingThirdFragment()
        )

        val viewPagerAdapter = ViewPagerAdapter(
            list = fragmentList,
            fragmentManager = requireActivity().supportFragmentManager,
            lifecycle = lifecycle
        )

        binding.vpViewPager.adapter = viewPagerAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}