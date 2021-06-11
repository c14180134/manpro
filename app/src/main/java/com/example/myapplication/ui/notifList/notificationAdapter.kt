package com.example.myapplication.ui.notifList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datanotif.notifItems
import com.example.myapplication.datanotif.notifViewModel
import kotlinx.android.synthetic.main.notif_item.view.*

class notificationAdapter (
    var items: List<notifItems>,
    private val viewModel: notifViewModel):RecyclerView.Adapter<notificationAdapter.notificationViewHolder>(){
        inner class notificationViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notificationViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.notif_item,parent,false)
        return notificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: notificationViewHolder, position: Int) {
        val curnotifitem=items[position]
        holder.itemView.namanotif.text=curnotifitem.notifNamex
        holder.itemView.delete_buttonnotif.setOnClickListener {
            viewModel.delete(curnotifitem)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}