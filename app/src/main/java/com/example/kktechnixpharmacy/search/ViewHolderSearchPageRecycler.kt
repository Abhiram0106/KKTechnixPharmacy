package com.example.kktechnixpharmacy.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.kktechnixpharmacy.R
import com.example.kktechnixpharmacy.databinding.SearchItemBinding
import com.example.kktechnixpharmacy.databinding.SearchItemStoreBinding
import com.example.kktechnixpharmacy.databinding.SearchRecyclerviewTitleBinding

sealed class ViewHolderSearchPageRecycler(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    var itemClickListenerInViewHolder : ( (view: View, item: SearchRecyclerViewItem, position: Int) -> Unit )? = null

    class ViewHolderTitle(
        private val binding: SearchRecyclerviewTitleBinding
    ) : ViewHolderSearchPageRecycler(binding) {

        fun bind(title: SearchRecyclerViewItem.Title){
            binding.tvTitle.text = title.title
            binding.root.setOnClickListener { view ->
                itemClickListenerInViewHolder?.invoke(view, title, adapterPosition)
            }
        }
    }

    class ViewHolderProduct(
        private val binding: SearchItemBinding
    ) : ViewHolderSearchPageRecycler(binding) {

        fun bind(product: SearchRecyclerViewItem.Product) = binding.apply {
            ivProductImage.setImageResource(product.img)
            tvTitle.text = product.name
            tvDesc.text = tvDesc.context.getString(R.string.price, product.price)

            binding.root.setOnClickListener { view ->
                itemClickListenerInViewHolder?.invoke(view, product, adapterPosition)
            }
        }
    }

    class ViewHolderStore(private val binding: SearchItemStoreBinding
    ) : ViewHolderSearchPageRecycler(binding) {

        fun bind(store: SearchRecyclerViewItem.Store) = binding.apply {
            ivProductImage.setImageResource(store.img)
            tvTitle.text = store.name
            tvDesc.text = store.desc

            binding.root.setOnClickListener { view ->
                itemClickListenerInViewHolder?.invoke(view, store, adapterPosition)
            }
        }
    }
}