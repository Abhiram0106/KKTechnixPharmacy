package com.example.kktechnixpharmacy.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kktechnixpharmacy.R
import com.example.kktechnixpharmacy.databinding.CartListItemBinding

class RecyclerViewAdapterCart(
    var productsList: List<ProductData>,
    private var onDecrementClicked: ((quantity:Int, price: Int, position:Int) -> Unit),
    private var onIncrementClicked: ((quantity: Int, price: Int, position:Int) -> Unit)
    ) : RecyclerView.Adapter<RecyclerViewAdapterCart.ViewHolderCart>() {

    inner class ViewHolderCart(private val binding: CartListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductData) = binding.apply {
            tvProductName.text = product.name
            tvProductIngredientName.text = product.ingredient
            tvProductPrice.text = itemView.context.getString(R.string.price,product.price)
            tvProductQuantityCounter.text = product.counter.toString()

            ivProductImage.setImageResource(R.drawable.ic_baseline_masks_24)

            ibDecrementQuantity.setOnClickListener {
                onDecrementClicked(product.counter,product.price,adapterPosition)
                notifyItemChanged(adapterPosition)
            }
            ibIncrementQuantity.setOnClickListener{
                onIncrementClicked(product.counter,product.price,adapterPosition)
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCart {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolderCart(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderCart, position: Int) {

        holder.bind(productsList[position])
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}