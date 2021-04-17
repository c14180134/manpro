package com.example.myapplication.datalokasi

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addLocation(location:Location)

    @Update
    suspend fun updateUser(location:Location)

    @Delete
    suspend fun deleteLocation(location: Location)

    @Query("DELETE FROM LOCATION_TABLE")
    suspend fun deleteAllLocation()

    @Query("SELECT * FROM LOCATION_TABLE ORDER BY id ASC")
    fun readAllData(): LiveData<List<Location>>
}