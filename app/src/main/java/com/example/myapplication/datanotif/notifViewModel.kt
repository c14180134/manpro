package com.example.myapplication.datanotif

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class notifViewModel(private val repository: notificationRepository): ViewModel() {
    fun insert(item: notifItems)= CoroutineScope(Dispatchers.Main).launch {
        repository.insert(item)
    }

    fun delete(item: notifItems)= CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingitems()=repository.getAllnotifitems()

}