package com.geeks.appshop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.geeks.appshop.databinding.ItemCartProductBinding
import com.geeks.appshop.domain.model.CartItem

class CartAdapter : ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    class CartDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvTitle.text = item.product.title
            tvPrice.text = item.product.price.toString()
            tvQuantity.text = "x${item.quantity}"

            ivProduct.load(item.product.image)
        }
    }

    class CartViewHolder(val binding: ItemCartProductBinding) : RecyclerView.ViewHolder(binding.root)

}