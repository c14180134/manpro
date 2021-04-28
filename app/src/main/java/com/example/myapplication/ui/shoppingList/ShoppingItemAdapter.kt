package com.example.myapplication.ui.shoppingList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datashoppinglist.db.ShoppingViewModel
import com.example.myapplication.datashoppinglist.db.shopentities.ShoppingItem
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var item:List<ShoppingItem>,
    private val viewModel:ShoppingViewModel
):RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem=item[position]

        holder.itemView.tvName.text=curShoppingItem.itemName
        holder.itemView.tvAmount.text="${curShoppingItem.itemAmount}"

        holder.itemView.tvDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.itemView.tvPlus.setOnClickListener {
            curShoppingItem.itemAmount++
            viewModel.upsert(curShoppingItem)
        }

        holder.itemView.tvMinus.setOnClickListener {
            if(curShoppingItem.itemAmount>0) {
                curShoppingItem.itemAmount--
                viewModel.upsert(curShoppingItem)
            }
        }

    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class ShoppingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}