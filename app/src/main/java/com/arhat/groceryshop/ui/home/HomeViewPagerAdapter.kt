package com.arhat.groceryshop.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arhat.groceryshop.R

class HomeViewPagerAdapter() :
    RecyclerView.Adapter<HomeViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder =
        ViewPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_home_view_pager_image, parent, false)
        )

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = 3


}
