package com.example.myapplication.datamanagestok

class ManageRepository(
    private val db: ManageDatabase
) {
    suspend fun upsert(item: ManageStock) = db.getManageDao().upsert(item)

    suspend fun delete(item: ManageStock) = db.getManageDao().delete(item)

    fun getAll() = db.getManageDao().getAll()
}