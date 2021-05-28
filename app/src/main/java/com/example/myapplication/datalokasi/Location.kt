package com.example.myapplication.datalokasi


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "location_table")
data class Location (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val Name : String,
        val Longitude : Double,
        val Latitude: Double,
        val Notes : String,
        val Nilai : Float
): Parcelable