package com.example.myapplication.ui.shoppingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datashoppinglist.db.ShoppingDatabase
import com.example.myapplication.datashoppinglist.db.ShoppingViewModel
import com.example.myapplication.datashoppinglist.db.ShoppingViewModelFactory
import com.example.myapplication.datashoppinglist.db.shopentities.ShoppingItem
import com.example.myapplication.datashoppinglist.db.shoppingRepo.ShoppingRepository
import com.example.myapplication.ui.AddDialogListener
import com.example.myapplication.ui.AddShoppingItemDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_shoppinglist.*

class shoppinglist : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
         val view = inflater.inflate(R.layout.fragment_shoppinglist, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //shoppinglist
        val database = ShoppingDatabase(this.requireContext())
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModelShop= ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)
        val adapters = ShoppingItemAdapter(listOf(),viewModelShop)
        val recyview= view.findViewById<RecyclerView>(R.id.rvShoppingItems)
        recyview.adapter=adapters
        recyview.layoutManager = LinearLayoutManager(requireContext())


        viewModelShop.getAllShoppingitems().observe(viewLifecycleOwner, Observer {
            adapters.item=it
            adapters.notifyDataSetChanged()
        })
        val fabsh: FloatingActionButton =requireView().findViewById<FloatingActionButton>(R.id.fabshop)
        fabsh.setOnClickListener {
            //memanggil fungsi addshopingitemdialog yang memanggil window untuk menambahkan barang
            AddShoppingItemDialog(requireContext(),object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    //upsert digunakan sebagai livedata +- update
                    viewModelShop.upsert(item)
                }
            }).show()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}