package com.example.myapplication.ui.stock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datalokasi.LocationViewModel
import com.example.myapplication.datamanagestok.ManageDatabase
import com.example.myapplication.datamanagestok.ManageRepository
import com.example.myapplication.datamanagestok.ManageStock
import com.example.myapplication.datashoppinglist.db.ShoppingDatabase
import com.example.myapplication.datashoppinglist.db.ShoppingViewModel
import com.example.myapplication.datashoppinglist.db.ShoppingViewModelFactory
import com.example.myapplication.datashoppinglist.db.shoppingRepo.ShoppingRepository
import com.example.myapplication.ui.AddDialogListener
import com.example.myapplication.ui.AddDialogListenerStock
import com.example.myapplication.ui.shoppingList.ShoppingItemAdapter
import kotlinx.android.synthetic.main.fragment_gudangstock.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class gudangstock : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_gudangstock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = ManageDatabase(this.requireContext())
        val repository = ManageRepository(database)
        val factory = ManageViewModelFactory(repository)


        val viewModel= ViewModelProvider(this,factory).get(ManageViewModel::class.java)


        val adapter = ManageStockAdapter(listOf(), viewModel)

        rvManageStock.adapter = adapter
        rvManageStock.layoutManager = LinearLayoutManager(requireContext())


        viewModel.getAll().observe(viewLifecycleOwner, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fabstock.setOnClickListener {
            AddManageStockDialog(requireContext(),
                object : AddDialogListenerStock {
                    override fun onAddButtonClicked(item: ManageStock) {
                        viewModel.upsert(item)
                    }
                }).show()
        }


    }

}