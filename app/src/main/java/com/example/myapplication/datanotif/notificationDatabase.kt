package com.example.myapplication.datanotif

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
        entities = [notifItems::class],
        version =1
)
abstract class notificationDatabase : RoomDatabase(){
    abstract fun getNotifDao():notifDao
    companion object{
        @Volatile
        private var instance: notificationDatabase?=null

        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context)=
                Room.databaseBuilder(context.applicationContext,
                        notificationDatabase::class.java,"notificationDB.db").build()
    }
}