package com.example.myapplication.datalokasi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.launch

class LocationViewModel(application: Application):AndroidViewModel(application) {

    val readAllData : LiveData<List<Location>>

    private val repository:LocationRepository

    init {
        val locationDao = LocationDatabase.getDatabase(application).locationDao()
        repository = LocationRepository(locationDao)
        readAllData = repository.readAllData
    }

    fun addLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO){
            repository.addLocation(location)
        }
    }

    fun updateLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateLocation(location)
        }
    }

    fun deleteLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteLocation(location)
        }
    }

    fun deleteAllLocations(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllLocation()
        }
    }
    fun searchDatabase(searchQuery:String): LiveData<List<Location>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}