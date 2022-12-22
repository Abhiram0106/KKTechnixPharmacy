package com.example.kktechnixpharmacy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kktechnixpharmacy.databinding.PinnedLocListItemBinding

class RecyclerViewAdapterPinnedAddresses(
    private val pinnedAddressesList: List<PinnedAddressesData>
) : RecyclerView.Adapter<RecyclerViewAdapterPinnedAddresses.ViewHolderPinnedAddresses>() {

    inner class ViewHolderPinnedAddresses(private val binding: PinnedLocListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pinnedAddress: PinnedAddressesData)= binding.apply {
            ivLocImageUserSelected.setImageResource(pinnedAddress.icon)
            tvLocName.text = pinnedAddress.name
            tvLocAddress.text = pinnedAddress.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPinnedAddresses {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PinnedLocListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolderPinnedAddresses(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderPinnedAddresses, position: Int) {
        holder.bind(pinnedAddressesList[position])
    }

    override fun getItemCount(): Int {
        return pinnedAddressesList.size
    }
}