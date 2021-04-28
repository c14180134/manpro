package com.example.myapplication.datashoppinglist.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.datashoppinglist.db.shopentities.ShoppingItem

@Dao
interface ShoppingDao {
    //fungsi update insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM SHOPPING_ITEMS")
    fun getAllShoppingItems():LiveData<List<ShoppingItem>>
}