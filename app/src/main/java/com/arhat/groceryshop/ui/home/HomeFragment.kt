package com.arhat.groceryshop.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arhat.groceryshop.databinding.FragmentHomeBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeAdapter = HomeAdapter()
        binding?.apply {
            rvHome.apply {
                layoutManager = LinearLayoutManager(
                    view.context,
                    LinearLayoutManager.VERTICAL, false)
                adapter = homeAdapter
            }
            viewPagerHome.apply {
                adapter = HomeViewPagerAdapter()
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
        }
        subscribeUi(homeAdapter)
    }

    private fun subscribeUi(adapter: HomeAdapter) {
        homeViewModel.homeItemList.observe(viewLifecycleOwner) { homeItems ->
            adapter.submitList(homeItems)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}