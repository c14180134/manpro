package com.example.myapplication.datalokasi

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class LocationRepository(private val LocationDao:LocationDao) {
    val readAllData: LiveData<List<Location>> = LocationDao.readAllData()

    suspend fun updateLocation(location: Location){
        LocationDao.updateUser(location)
    }

    suspend fun addLocation( location: Location ){
        LocationDao.addLocation(location)
    }

    suspend fun deleteLocation(location: Location){
        LocationDao.deleteLocation(location)
    }
    suspend fun deleteAllLocation(){
        LocationDao.deleteAllLocation()
    }
    fun searchDatabase(searchQuery:String):Flow<List<Location>>{
        return LocationDao.searchDatabase(searchQuery)
    }

}