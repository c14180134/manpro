package com.example.myapplication.ui.stock

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.myapplication.R
import com.example.myapplication.datamanagestok.ManageStock
import com.example.myapplication.ui.AddDialogListener
import com.example.myapplication.ui.AddDialogListenerStock
import kotlinx.android.synthetic.main.dialog_add_manage_stock.*

class AddManageStockDialog(context: Context, var addDialogListener: AddDialogListenerStock): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_manage_stock)

        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()
            val reminder = etReminder.text.toString()

            if(name.isEmpty() || amount.isEmpty() || reminder.isEmpty()){
                Toast.makeText(context, "Please enter all information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ManageStock(name,amount.toInt(),reminder.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }
}