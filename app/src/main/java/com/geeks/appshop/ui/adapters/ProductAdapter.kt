package com.geeks.appshop.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.geeks.appshop.databinding.ItemProductBinding
import com.geeks.appshop.domain.model.Product

class ProductAdapter(

    private val onClick: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffUtilCallback()) {

    class ProductDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) = holder.bind(
        getItem(position)
    )


    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding) {
                tvTitle.text = product.title
                tvPrice.text = product.price.toString()
                tvCategory.text = product.category
                imgProduct.load(product.image) {
                    crossfade(true)
                }

                root.setOnClickListener {
                    onClick.invoke(product)
                }
            }
        }

    }


}