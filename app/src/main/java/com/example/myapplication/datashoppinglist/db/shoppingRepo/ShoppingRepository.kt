package com.example.myapplication.datashoppinglist.db.shoppingRepo

import com.example.myapplication.datashoppinglist.db.ShoppingDatabase
import com.example.myapplication.datashoppinglist.db.shopentities.ShoppingItem

class ShoppingRepository(
        private  val db:ShoppingDatabase
) {
    suspend fun upsert(item:ShoppingItem)=db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem)= db.getShoppingDao().delete(item)

    fun getAllShoppingitems()=db.getShoppingDao().getAllShoppingItems()
}