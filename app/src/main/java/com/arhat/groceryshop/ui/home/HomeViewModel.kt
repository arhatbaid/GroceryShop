package com.arhat.groceryshop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arhat.groceryshop.data.HomeItem
import com.arhat.groceryshop.data.Product

class HomeViewModel : ViewModel() {

    private val _homeItemList = MutableLiveData<List<HomeItem>>().apply {
        value = addItemsToRecyclerViewArrayList()
    }
    val homeItemList: LiveData<List<HomeItem>> = _homeItemList

    private fun addItemsToRecyclerViewArrayList(): List<HomeItem> {
        return List(10) { i -> HomeItem("Item $i", arrayListOf())  }
    }
}