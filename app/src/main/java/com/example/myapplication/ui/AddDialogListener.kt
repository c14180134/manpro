package com.example.myapplication.ui

import com.example.myapplication.datashoppinglist.db.shopentities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)

}