package com.example.myapplication.ui.stock

import androidx.lifecycle.ViewModel
import com.example.myapplication.datamanagestok.ManageRepository
import com.example.myapplication.datamanagestok.ManageStock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ManageViewModel(
    private val repository: ManageRepository
): ViewModel() {

    fun upsert(item: ManageStock) = CoroutineScope(Dispatchers.Main). launch {
        repository.upsert(item)
    }

    fun delete(item: ManageStock) = CoroutineScope(Dispatchers.Main). launch {
        repository.delete(item)
    }

    fun getAll() = repository.getAll()
}