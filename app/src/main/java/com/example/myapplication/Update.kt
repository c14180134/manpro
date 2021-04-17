package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.datalokasi.Location
import com.example.myapplication.datalokasi.LocationViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Update.newInstance] factory method to
 * create an instance of this fragment.
 */
class Update : Fragment() {

    private val args by navArgs<UpdateArgs>()
    private lateinit var mLocationViewModel:LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_update, container, false)
        view.locUpname.setText(args.currentLoc.Name)
        view.noteUp.setText(args.currentLoc.Notes)
        mLocationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        view.update_button.setOnClickListener{
            updateItem()
        }
        view.delete_button.setOnClickListener{
            deleteLoc()
        }

        return view
    }

    private fun updateItem(){
        val locationName = locUpname.text.toString()
        val notes= noteUp.text.toString()

        if(inputCheck(locationName,notes)){
            val updateLocNote = Location(args.currentLoc.id,locationName,args.currentLoc.Longitude,args.currentLoc.Latitude,notes)

            mLocationViewModel.updateLocation(updateLocNote)
            Toast.makeText(requireContext(),"Update Succesfully",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_update_to_nav_slideshow)
        }else{
            Toast.makeText(requireContext(),"Please Fill out all fields.", Toast.LENGTH_SHORT).show()
        }


    }
    private fun deleteLoc(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mLocationViewModel.deleteLocation(args.currentLoc)
            Toast.makeText(
                requireContext(),
                "Succesfully delete",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_update_to_nav_slideshow)
        }
        builder.setNegativeButton("No"){ _, _ ->}
        builder.setTitle("Delete this location ${args.currentLoc.Name}?")
        builder.setMessage("Are you sure want to delete it?")
        builder.create().show()
    }

    private fun inputCheck(LocName:String,Notee:String):Boolean{
        return !(TextUtils.isEmpty(LocName)&&TextUtils.isEmpty(Notee))
    }

}