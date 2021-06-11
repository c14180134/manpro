package com.example.myapplication.datanotif

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_items")
data class notifItems(
        @ColumnInfo(name ="notif_name")
        var notifNamex:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}
