package com.example.myapplication.ui.listsaveloc

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datalokasi.LocationViewModel

class listsaveloc : Fragment() {

    private lateinit var mLocationViewModel:LocationViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_listsaveloc, container, false)
        val adapter = ListlocAdapter()
        val recyview= view.findViewById<RecyclerView>(R.id.recylerview)
        recyview.adapter = adapter
        recyview.layoutManager = LinearLayoutManager(requireContext())

        mLocationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        mLocationViewModel.readAllData.observe(viewLifecycleOwner, Observer { location->
            adapter.setData(location)
        })
        return view


    }

}