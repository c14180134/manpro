package com.example.myapplication.datanotif

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface notifDao {
    //fungsi update insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: notifItems)

    @Delete
    suspend fun delete(item: notifItems)

    @Query("SELECT * FROM notification_items")
    fun getAllnotifItems(): LiveData<List<notifItems>>
}