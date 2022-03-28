package com.arhat.groceryshop.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arhat.groceryshop.data.HomeItem
import com.arhat.groceryshop.databinding.ListItemHomeCategoryBinding

class HomeAdapter : ListAdapter<HomeItem, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            ListItemHomeCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val homeTime = getItem(position)
        (holder as HomeViewHolder).bind(homeTime)
    }

    class HomeViewHolder(
        private val binding: ListItemHomeCategoryBinding,
        context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                Toast.makeText(context, "See All Clicked", Toast.LENGTH_LONG).show()
            }
        }

        fun bind(item: HomeItem) {
            binding.apply {
                homeItem = item
                executePendingBindings()
            }
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<HomeItem>() {

    override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.category == newItem.category
    }

    override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem == newItem
    }
}
