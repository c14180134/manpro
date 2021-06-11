package com.example.myapplication.ui.notifList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datanotif.notifViewModel
import com.example.myapplication.datanotif.notifViewModelFactory
import com.example.myapplication.datanotif.notificationDatabase
import com.example.myapplication.datanotif.notificationRepository


class notificationList : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_notification_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database=notificationDatabase(this.requireContext())
        val repository=notificationRepository(database)
        val factory=notifViewModelFactory(repository)

        val viewModelNotification= ViewModelProvider(this,factory).get(notifViewModel::class.java)

        val adapters=notificationAdapter(listOf(),viewModelNotification)
        val recyview= view.findViewById<RecyclerView>(R.id.rvNotif)
        recyview.adapter=adapters
        recyview.layoutManager=LinearLayoutManager(requireContext())

        viewModelNotification.getAllShoppingitems().observe(viewLifecycleOwner,{
            adapters.items=it
            adapters.notifyDataSetChanged()
        })


    }

}