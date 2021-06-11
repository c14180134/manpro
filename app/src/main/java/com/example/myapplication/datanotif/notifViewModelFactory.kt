package com.example.myapplication.datanotif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress ("UNCHECKED_CAST")
class notifViewModelFactory (

    private val repository: notificationRepository

        ):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return notifViewModel(repository)as T
    }
}