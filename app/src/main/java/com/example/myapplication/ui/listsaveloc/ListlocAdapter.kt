package com.example.myapplication.ui.listsaveloc

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datalokasi.Location
import com.example.myapplication.datalokasi.LocationViewModel
import kotlinx.android.synthetic.main.custom_rowloc.view.*


class ListlocAdapter: RecyclerView.Adapter<ListlocAdapter.MyViewHolder>() {

    var LocList = emptyList<Location>()
    private lateinit var mLocationViewModel:LocationViewModel

    class  MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.custom_rowloc,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return LocList.size
    }





    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = LocList[position]
        holder.itemView.findViewById<TextView>(R.id.locnames).text = currentItem.Name
        holder.itemView.findViewById<TextView>(R.id.comment).text = currentItem.Notes

        holder.itemView.rowLayout.setOnClickListener{
            val action = listsavelocDirections.actionNavSlideshowToUpdate(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(lokasi: List<Location>){
        this.LocList = lokasi
        notifyDataSetChanged()
    }

    fun getList(): List<Location> {

        return LocList
    }



}

