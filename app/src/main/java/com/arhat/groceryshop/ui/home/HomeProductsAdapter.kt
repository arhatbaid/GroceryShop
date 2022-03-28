package com.arhat.groceryshop.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arhat.groceryshop.data.Product
import com.arhat.groceryshop.databinding.ListItemProductBinding

class HomeProductAdapter : ListAdapter<Product, RecyclerView.ViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeProductViewHolder(
            ListItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        (holder as HomeProductViewHolder).bind(product)
    }

    class HomeProductViewHolder(
        private val binding: ListItemProductBinding,
        context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                Toast.makeText(context, "Product Item clicked", Toast.LENGTH_LONG).show()
            }
        }

        fun bind(item: Product) {
            binding.apply {
                product = item
                executePendingBindings()
            }
        }
    }
}

private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}
