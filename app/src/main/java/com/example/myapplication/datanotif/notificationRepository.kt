package com.example.myapplication.datanotif

class notificationRepository(
        private val db:notificationDatabase
) {
    suspend fun insert(items: notifItems)=db.getNotifDao().insert(items)
    suspend fun delete(items: notifItems)=db.getNotifDao().delete(items)
    fun getAllnotifitems()=db.getNotifDao().getAllnotifItems()
}