package com.example.kktechnixpharmacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.kktechnixpharmacy.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    val SHIPPING_COST = 50

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btProceed.isClickable = false

        binding.tvShippingAddress.text = "Abhiram\nHouse 123, 456 street, abc, xyz.\n+91 1234567890"

        val mrp = 500
        binding.tvMRPValue.text = view.context.getString(R.string.price, mrp)

        val discount = 0
        binding.tvDiscountValue.text = view.context.getString(R.string.price, discount)

        binding.rbFreeDeliveryMethod.isChecked = true
        binding.tvShippingValue.text = view.context.getString(R.string.price, 0)
        binding.tvTotalValue.text = view.context.getString(R.string.price, mrp-discount+0)

        binding.rbPaidDeliveryMethod.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                val shipping = SHIPPING_COST
                binding.tvShippingValue.text = view.context.getString(R.string.price, shipping)
                binding.tvTotalValue.text = view.context.getString(R.string.price, mrp-discount+shipping)
            }
            else {
                val shipping = 0
                binding.tvShippingValue.text = view.context.getString(R.string.price, shipping)
                binding.tvTotalValue.text = view.context.getString(R.string.price, mrp-discount+shipping)
            }
        }

        binding.rbPaidDeliveryMethod.text = view.context.getString(R.string.delivery_method_paid, 50)

        binding.btProceed.setOnClickListener{
            if(binding.rgPaymentMethods.checkedRadioButtonId != -1 && binding.rgDeliveryMethods.checkedRadioButtonId != -1)
            {
                Toast.makeText(view.context, "Proceed", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, "Select Payment Method", Toast.LENGTH_SHORT).show()
            }

        }

        binding.fabGoToSearchPage.setOnClickListener {
            val directions = PaymentFragmentDirections.actionPaymentFragmentToSearchProductFragment()
            view.findNavController().navigate(directions)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}