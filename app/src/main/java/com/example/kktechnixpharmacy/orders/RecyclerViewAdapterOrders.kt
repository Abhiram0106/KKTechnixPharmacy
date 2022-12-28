package com.example.kktechnixpharmacy.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kktechnixpharmacy.R
import com.example.kktechnixpharmacy.databinding.OrdersListItemBinding

class RecyclerViewAdapterOrders(var ordersList: List<OrderedProductData>) : RecyclerView.Adapter<RecyclerViewAdapterOrders.ViewHolderOrders>() {

    inner class ViewHolderOrders(private val binding: OrdersListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(orderedProduct: OrderedProductData) = binding.apply {

            tvStatus.text = orderedProduct.status

            when(orderedProduct.status) {
                itemView.context.getString(R.string.waiting_for_payment) -> {
                    tvStatus.setBackgroundColor(itemView.context.getColor(R.color.yellow_payment_wait))
                }

                itemView.context.getString(R.string.failed) -> {
                tvStatus.setBackgroundColor(itemView.context.getColor(R.color.red_payment_fail))
                }

                itemView.context.getString(R.string.delivered) -> {
                tvStatus.setBackgroundColor(itemView.context.getColor(R.color.green_order_delivered))
                }

                itemView.context.getString(R.string.ongoing) -> {
                tvStatus.setBackgroundColor(itemView.context.getColor(R.color.grey_order_ongoing))
                }
            }

            tvOrderDate.text = orderedProduct.date
            tvTransactionID.text = orderedProduct.transactionID
            tvAddress.text = orderedProduct.address
            tvTotalPayment.text = orderedProduct.payment.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderOrders {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OrdersListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolderOrders(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderOrders, position: Int) {
        holder.bind(ordersList[position])
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    fun updateList(ordersList: List<OrderedProductData>) {
        this.ordersList = ordersList
        notifyDataSetChanged()
    }
}