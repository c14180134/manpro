package com.example.myapplication.ui

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datashoppinglist.db.shopentities.ShoppingItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*
//fungsi class yang menampilkan item dialog
class AddShoppingItemDialog(context: Context,var addDialogListener: AddDialogListener):AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //resource layout yang digunakan untuk inflate pada bagian fragment
        setContentView(R.layout.dialog_add_shopping_item)
        //bagian tvAdd merupakan bagian dari layoutdialogadd
        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount= etAmount.text.toString()

            if(name.isEmpty()||amount.isEmpty()){
                Toast.makeText(context,"Please fill in the input of name or amount",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name,amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }
        tvCancel.setOnClickListener {
            cancel()
        }

    }
}