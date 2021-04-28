package com.example.myapplication.datashoppinglist.db

import androidx.lifecycle.ViewModel
import com.example.myapplication.datashoppinglist.db.shopentities.ShoppingItem
import com.example.myapplication.datashoppinglist.db.shoppingRepo.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
        private val repository: ShoppingRepository
):ViewModel() {

    fun upsert(item: ShoppingItem)= CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem)= CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingitems()=repository.getAllShoppingitems()
}