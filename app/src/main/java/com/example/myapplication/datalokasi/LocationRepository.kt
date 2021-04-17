package com.example.myapplication.datalokasi

import androidx.lifecycle.LiveData

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

}