package com.example.myapplication.datamanagestok

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "manage_stock")
data class ManageStock (
    @ColumnInfo(name="item_name")
    var name: String,
    @ColumnInfo(name = "item_amount")
    var amount: Int,
    @ColumnInfo(name = "item_reminder")
    var reminder: Int
){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}