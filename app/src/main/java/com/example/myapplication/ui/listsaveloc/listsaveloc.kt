package com.example.myapplication.ui.listsaveloc

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datalokasi.Location
import com.example.myapplication.datalokasi.LocationViewModel
import kotlinx.android.synthetic.main.fragment_listsaveloc.*

class listsaveloc : Fragment(){

    private lateinit var mLocationViewModel:LocationViewModel
    private var LocList = emptyList<Location>()
    var qterima:String?=""
    val adapter = ListlocAdapter()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //menerima bundle dari home fragment
        qterima=arguments?.getString("message")
        val view =inflater.inflate(R.layout.fragment_listsaveloc, container, false)

        val recyview= view.findViewById<RecyclerView>(R.id.recylerview)
        recyview.adapter = adapter
        recyview.layoutManager = LinearLayoutManager(requireContext())

        //buat manggil database ke fragment
        mLocationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        //fungsi memanggillive data
        mLocationViewModel.readAllData.observe(viewLifecycleOwner, Observer { location->
            adapter.setData(location)//set adapter untuk dapat dijadikan tempat sementara livedata
            LocList=adapter.getList()//dimasukkan tempat sementara loclist
            Log.d("MAMA",LocList[0].Notes)
            if(qterima!=null){
                searchView.onActionViewExpanded()
            }

            //fungsi filter by text
            searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query !=null){
                        searchDatabase(query)
                    }
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (query !=null){
                        searchDatabase(query)
                    }
                    return true
                }
            })
            searchView.setQuery(qterima,true)

        })

        return view


    }

    //fungsi query database ada di location database DAO
    private fun searchDatabase(query:String){
        val searchQuery = "%$query%"
        mLocationViewModel.searchDatabase(searchQuery).observe(this,{ list ->
              list.let {
                adapter.setData(it)
              }
        })

    }
}