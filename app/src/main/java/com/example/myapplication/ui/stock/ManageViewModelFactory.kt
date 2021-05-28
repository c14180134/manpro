package com.example.myapplication.ui.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.datamanagestok.ManageRepository

@Suppress("UNCHECKED_CAST")
class ManageViewModelFactory(
    private val repository: ManageRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ManageViewModel(repository) as T
    }
}