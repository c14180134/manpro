package com.example.myapplication.datamanagestok

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ManageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ManageStock)

    @Delete()
    suspend fun delete(item: ManageStock)

    @Query("SELECT * FROM manage_stock")
    fun getAll(): LiveData<List<ManageStock>>
}