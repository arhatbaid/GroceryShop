package com.arhat.groceryshop.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.arhat.groceryshop.data.HomeItem
import com.arhat.groceryshop.databinding.ListItemHomeCategoryBinding
import com.arhat.groceryshop.databinding.ListItemHomeHeaderViewPagerBinding

class HomeAdapter : ListAdapter<HomeItem, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    companion object {
        private const val TYPE_VIEW_PAGER_BANNER = 0
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ViewBinding
        when (viewType) {
            TYPE_VIEW_PAGER_BANNER -> {
                binding = ListItemHomeHeaderViewPagerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewPagerViewHolder(binding)
            }
            else -> {
                binding = ListItemHomeCategoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return HomeViewHolder(binding, parent.context)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val homeItem = getItem(position)
        when (position) {
            TYPE_VIEW_PAGER_BANNER -> {
                (holder as ViewPagerViewHolder).bind()
            }
            else -> {
                (holder as HomeViewHolder).bind(homeItem)
            }
        }
    }

    class HomeViewHolder(
        private val binding: ListItemHomeCategoryBinding,
        context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var homeProductAdapter: HomeProductAdapter

        init {
            binding.setClickListener {
                Toast.makeText(context, "See All Clicked", Toast.LENGTH_LONG).show()
            }
        }

        fun bind(item: HomeItem) {
            homeProductAdapter = HomeProductAdapter()
            binding.apply {
                homeItem = item
                rvHomeCategory.apply {
                    layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    adapter = homeProductAdapter
                    homeProductAdapter.submitList(item.categoryData)
                }
                executePendingBindings()
            }
        }
    }

    class ViewPagerViewHolder(
        private val binding: ListItemHomeHeaderViewPagerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var homeProductAdapter: HomeProductAdapter

        fun bind() {
            homeProductAdapter = HomeProductAdapter()
            binding.apply {
                viewPagerHome.apply {
                    adapter = HomeViewPagerAdapter()
                    orientation = ViewPager2.ORIENTATION_HORIZONTAL
                }
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
