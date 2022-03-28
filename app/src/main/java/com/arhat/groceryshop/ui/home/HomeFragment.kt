package com.arhat.groceryshop.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arhat.groceryshop.databinding.FragmentHomeBinding
import androidx.recyclerview.widget.LinearLayoutManager


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeAdapter = HomeAdapter()
        binding?.rvHome?.apply {
            layoutManager = LinearLayoutManager(
                view.context,
                LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
            subscribeUi(homeAdapter)
        }
    }

    private fun subscribeUi(adapter: HomeAdapter) {
        homeViewModel.homeItemList.observe(viewLifecycleOwner) { plants ->
            adapter.submitList(plants)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}