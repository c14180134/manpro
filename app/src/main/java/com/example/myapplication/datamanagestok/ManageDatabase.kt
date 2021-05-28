package com.example.myapplication.datamanagestok

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ManageStock::class],
    version = 1
)
abstract class ManageDatabase: RoomDatabase() {

    abstract fun getManageDao(): ManageDao

    companion object {
        @Volatile
        private var instance: ManageDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    )
                        .also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ManageDatabase::class.java, "ManageDB.db").build()
    }
}